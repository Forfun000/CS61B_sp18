public class Planet {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  /** constructor of the Planet using attribute */
  public Planet(double xP, double yP, double xV,
      double yV, double m, String img) {
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
  }

  /** constructor of the planet using another planet */
  public Planet(Planet p) {
    this.xxPos = p.xxPos;
    this.yyPos = p.yyPos;
    this.xxVel = p.xxVel;
    this.yyVel = p.yyVel;
    this.mass = p.mass;
    this.imgFileName = p.imgFileName;
  }

  /** calculates the distance between two Planets */
  public double calcDistance(Planet p) {
    double r, xxDistance, yyDistance;
    xxDistance = p.xxPos - this.xxPos;
    yyDistance = p.yyPos - this.yyPos;
    r = Math.sqrt(xxDistance * xxDistance + yyDistance * yyDistance);
    return r;
  }

  /**
   * return a double describing the force exerted on this planet by the given
   * planet
   */
  public double calcForceExertedBy(Planet p) {
    double r = this.calcDistance(p); // distance
    double G = 6.67e-11; // gravitational constant
    double force;
    force = G * this.mass * p.mass / (r * r);
    return force;
  }

  /** take in a Planet and calculate the net X force exerted by the Planet */
  public double calcForceExertedByX(Planet p) {
    double xxDistance = p.xxPos - this.xxPos;
    return this.calcForceExertedBy(p) * xxDistance / this.calcDistance(p);
  }

  /** take in a Planet and calculate the net Y force exerted by the Planet */
  public double calcForceExertedByY(Planet p) {
    double yyDistance = p.yyPos - this.yyPos;
    return this.calcForceExertedBy(p) * yyDistance / this.calcDistance(p);
  }

  /**
   * take in an array of Planets and calculate the net X force exerted by all
   * planets in that array upon the current Planet
   */
  public double calcNetForceExertedByX(Planet[] allPlanets) {
    double sumforceExertedByX = 0;
    Planet p;
    for (int i = 0; i < allPlanets.length; i++) {
      p = allPlanets[i];
      if (this.equals(p)) {
        continue;
      } else {
        sumforceExertedByX += this.calcForceExertedByX(p);
      }
    }
    return sumforceExertedByX;
  }

  /**
   * take in an array of Planets and calculate the net Y force exerted by all
   * planets in that array upon the current Planet
   */
  public double calcNetForceExertedByY(Planet[] allPlanets) {
    double sumforceExertedByY = 0;
    Planet p;
    for (int i = 0; i < allPlanets.length; i++) {
      p = allPlanets[i];
      if (this.equals(p)) {
        continue;
      } else {
        sumforceExertedByY += this.calcForceExertedByY(p);
      }
    }
    return sumforceExertedByY;
  }

  /**
   * determines how much the forces exerted on the planet will cause that planet
   * to accelerate
   */
  public void update(double dt, double fX, double fY) {
    double xxAcc, yyAcc;
    xxAcc = fX / this.mass;
    yyAcc = fY / this.mass;
    this.xxVel += xxAcc * dt;
    this.yyVel += yyAcc * dt;
    this.xxPos += this.xxVel * dt;
    this.yyPos += this.yyVel * dt;
  }

  /** draw itself at its appropriate position */
  public void draw() {
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }
}