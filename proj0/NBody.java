/** simulate a universe specified in one of the data files */
public class NBody {

  /** return a double corresponding to the radius of the universe in the file */
  public static double readRadius(String data) {
    In in = new In(data);
    in.readInt();
    return in.readDouble();
  }

  /** return an array of Planets corresponding to the planets in the file */
  public static Planet[] readPlanets(String data) {
    double xxPos, yyPos, xxVel, yyVel, mass;
    String planetName;
    In in = new In(data);
    int n = in.readInt(); // number of planets;
    in.readDouble(); // skip the radius of the universe
    Planet[] planets = new Planet[n];
    for (int i = 0; i < planets.length; i++) {
      xxPos = in.readDouble();
      yyPos = in.readDouble();
      xxVel = in.readDouble();
      yyVel = in.readDouble();
      mass = in.readDouble();
      planetName = in.readString();
      planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, planetName);
    }
    return planets;
  }

  /** draw the universe in its starting position */
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]); // total time
    double dt = Double.parseDouble(args[1]); // min time
    String filename = args[2];
    double radius = NBody.readRadius(filename);
    Planet[] planets = NBody.readPlanets(filename);
    int n = planets.length;

    StdDraw.enableDoubleBuffering();

    StdDraw.setScale(-radius, radius);
    StdDraw.picture(radius, radius, "images/starfield.jpg");

    for (Planet planet : planets) {
      planet.draw();
    }

    for (double time = 0; time < T; time += dt) {
      double[] xForces = new double[n];
      double[] yForces = new double[n];
      for (int i = 0; i < n; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for (int i = 0; i < n; i++)
        planets[i].update(dt, xForces[i], yForces[i]);

      StdDraw.picture(0, 0, "images/starfield.jpg");
      for (Planet planet : planets) {
        planet.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel,
          planets[i].mass, planets[i].imgFileName);
    }
  }

}
