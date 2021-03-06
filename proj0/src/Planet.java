import examples.StdDraw;

/** This program mimics the universe by applying Newton's law of universal gravitation
 * @author Thang Cao
 * @date 06/01/2020
 * */

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11; // 6.67 * 10^(-11)

    public Planet(double xxPos, double yyPos, double xxVel,
                  double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
       // StdDraw.show(); causes drawing to shutter
    }


    /** update the planet's position and velocity instance variables
     * velocityX = */
    public void update(double dt, double fX, double fY){
        double currentXPos = xxPos,
                currentYPos = yyPos,
                currentXVel = xxVel,
                currentYVel = yyVel;

        double accelerationX = acceleration(fX, mass);
        double accelerationY = acceleration(fY, mass);

        xxVel = newVel(currentXVel, accelerationX, dt);
        yyVel = newVel(currentYVel, accelerationY, dt);

        xxPos = newPosition(currentXPos,xxVel,dt);
        yyPos = newPosition(currentYPos,yyVel,dt);

    }

    /** newPos = Px + dt * Vx */
    public double newPosition(double currentPos, double newVel, double dt){
        return currentPos + newVel * dt;
    }

    /** (Vx + dt * Ax, Vy + dy * Ay)
     * Vx = xxVel*/
    public double newVel(double currentVel, double acceleration, double dt){
        return currentVel + acceleration * dt;
    }

    /** accelerationX = fX / m. (x direction) */
    public double acceleration(double force, double mass){
        return force / mass;
    }

    /** Calculates the net force(in x direction) exerted by all surrounding planets
     * F(netX) = (F*dx) / r*/
    public double calcNetForceExertedByX(Planet[] planets){
        double dx = 0;
        double netForceX = 0;
        double gForce = 0;
        double r = 0;

        for (Planet p : planets){
            if (this.equals(p)){
                continue;
            }
            gForce = gravitationalForce(p);
            dx = p.xxPos - xxPos;
            r = calcDistance(p);

            netForceX += (gForce * dx) / r;
        }

        return netForceX;
    }

    /** Calculates the net force(in y direction) exerted by all surrounding planets
     * F(netY) = (F*dy) / r */
    public double calcNetForceExertedByY(Planet[] planets){
        double dy = 0;
        double netForceY = 0;
        double gForce = 0;
        double r = 0;

        for (Planet p : planets){
            if (this.equals(p)){
                continue;
            }
            gForce = gravitationalForce(p);
            dy = p.yyPos - yyPos;
            r = calcDistance(p);

            netForceY += (gForce * dy) / r;
        }

        return netForceY;
    }


    /** takes in a planet object and calculate the distance
     * between this planet and planet p
     * r = sqrt(dx^2 + dy^2) */
    public double calcDistance(Planet p){
        double r = 0;
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;

        r = Math.sqrt(dx*dx + dy*dy);

        return r;
    }


    /** return gravitational force between 'this' planet and planet 'p'
     * F = (G*m1*m2) / r^2*/
    public double gravitationalForce(Planet p){
        double r = calcDistance(p);

        return (G * mass * p.mass) / (r * r);
    }
}
