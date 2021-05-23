/*
 * @Author: your name
 * @Date: 2021-05-21 23:11:00
 * @LastEditTime: 2021-05-22 11:34:43
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \proj0\Body.java
 */

public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;


    public Planet(double xP, double yP, double xV,
                double yV, double m, String img){
                    this.xxPos = xP;
                    this.yyPos = yP;
                    this.xxVel = xV;
                    this.yyVel = yV;
                    this.mass = m;
                    this.imgFileName = img;
                }
                

    public Planet(Planet b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet other){
        return Math.sqrt((other.xxPos - xxPos) * (other.xxPos - xxPos) + (other.yyPos - yyPos) * (other.yyPos - yyPos));
    }

    public double calcForceExertedBy(Planet other){
        double dist = this.calcDistance(other);
        return G * mass * other.mass / (dist * dist);
    }

    public double calcForceExertedByX(Planet other){
        return this.calcForceExertedBy(other) * (other.xxPos - this.xxPos)/ this.calcDistance(other);
    }

    public double calcForceExertedByY(Planet other){
        return this.calcForceExertedBy(other) * (other.yyPos - this.yyPos)/ this.calcDistance(other);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double calx = 0;

        for(Planet other: allPlanets){
            if(this.equals(other))
                continue;
            calx = calx + this.calcForceExertedByX(other);
        }
        return calx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double caly = 0;

        for(Planet other: allPlanets){
            if(this.equals(other))
                continue;
            caly = caly + this.calcForceExertedByY(other);
        }
        return caly;
    }

    public void update(double dt, double fX, double fY){
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
    }

}