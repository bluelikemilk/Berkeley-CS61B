public class Planet {
    // instance variables
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // constructors
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    // constructor by copy
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    // calculate the distance between two planets
    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    // calculate the force exerted by p
    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double r = calcDistance(p);
        double m1 = mass;
        double m2 = p.mass;
        double F = G*m1*m2/r/r;
        return F;
    }

    // calculate the force exerted by p in x direction
    public double calcForceExertedByX(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dx = p.xxPos - xxPos;
        double Fx = F*dx/r;
        return Fx;
    }

    // calculate the force exerted by p in y direction
    public double calcForceExertedByY(Planet p) {
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dy = p.yyPos - yyPos;
        double Fy = F*dy/r;
        return Fy;
    }

    // calculate net force exerted by AllPlanets in X direction
    public double calcNetForceExertedByX(Planet[] AllPlanets) {
        double NetFx = 0;
        for(Planet p : AllPlanets) { // enhanced loop
            if(p.equals(this)) {continue;}
            NetFx += calcForceExertedByX(p);
        }
        return NetFx;
    }

    // calculate net force exerted by AllPlanets in Y direction
    public double calcNetForceExertedByY(Planet[] AllPlanets) {
        double NetFy = 0;
        for(Planet p : AllPlanets) {
            if(p.equals(this)) {continue;}
            NetFy += calcForceExertedByY(p);
        }
        return NetFy;
    }

    // update the position of the planet based on acceleration and time
    public void update(double dt, double fX, double fY){
        // calculate acceleration from forces
        double ax = fX/mass;
        double ay = fY/mass;
        // calculate new velocity
        xxVel += dt * ax;
        yyVel += dt * ay;
        // calculate new positions
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;

    }

    // draw this planet use stdDraw
    public void draw(String folder) {
        StdDraw.picture(xxPos, yyPos, folder + imgFileName);
    }


}
