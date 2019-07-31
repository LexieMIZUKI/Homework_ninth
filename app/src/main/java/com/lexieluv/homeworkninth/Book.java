package com.lexieluv.homeworkninth;


public class Book {
    private String name;
    private boolean his,sus,lite;
    private int age;
    private int pic;

    public Book(){
    }

    public Book(String name, boolean his, boolean sus, boolean lite, int age, int pic) {
        this.name = name;
        this.his = his;
        this.sus = sus;
        this.lite = lite;
        this.age = age;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public boolean isHis() {
        return his;
    }

    public void setHis(boolean his) {
        this.his = his;
    }

    public boolean isSus() {
        return sus;
    }

    public void setSus(boolean sus) {
        this.sus = sus;
    }

    public boolean isLite() {
        return lite;
    }

    public void setLite(boolean lite) {
        this.lite = lite;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPic(){
        return pic;
    }

    public void setPic(int pic){
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + getName() + '\'' +
                ", his=" + isHis() +
                ", sus=" + isSus() +
                ", lite=" + isLite() +
                ", age=" + getAge() +
                ", pic=" + getPic() +
                '}';
    }
}
