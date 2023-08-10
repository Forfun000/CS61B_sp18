public class Test {
    public static void main(String[] args) {
        Animal a = new Animal("name");
        Cat c = new Cat("catname", "blue");
        Dog d = new Dog("dogname", "black");
        System.out.println(a instanceof Animal);
        System.out.println(c instanceof Animal);
        System.out.println(d instanceof Animal);
        System.out.println(c instanceof Cat);
        System.out.println(d instanceof Dog);

        a = new Dog("BigYellow", "yellow");// 向上转型 父类引用a指向了子类对象
        System.out.println(a instanceof Animal);
        System.out.println(a instanceof Dog);
        System.out.println(a.name);
        // System.out.println(a.furColor);//编译报错。
        // 因为一个父类的引用不可以访问其子类对象新增加的成员（属性和方法）

        Dog d1 = (Dog) a;// 强制转换，向下转型。子类引用指向父类对象
        System.out.println(d1.furColor);
    }
}

class Animal {
    public String name;

    Animal(String name) {
        this.name = name;
    }
}

class Cat extends Animal {
    public String eyeColor;

    Cat(String name, String eyeColor) {
        super(name);
        this.eyeColor = eyeColor;
    }
}

class Dog extends Animal {
    public String furColor;

    Dog(String name, String furColor) {
        super(name);
        this.furColor = furColor;
    }
}
