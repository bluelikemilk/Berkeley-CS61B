public class NBody {
    // read the radius of the universe from file
    public static double readRadius(String f) {
        In file = new In(f);
        if(file.exists()) {
            file.readLine();
            double radius= file.readDouble();
            return radius;
        }
        else
        {
            System.out.println("File name is invalid!");
            return 0;
        }
    }

    // read all planets from file
    public static Planet[] readPlanets(String f) {
        In file = new In(f);
        if(file.exists()) {
            int N = file.readInt(); // read number of planets
            Planet[] AllPlanets = new Planet[N];
            file.readDouble(); // skip radius of universe
            for(int i = 0; i < N; i++) {
                // load data of i-th planet
                double xxPos = file.readDouble();
                double yyPos = file.readDouble();
                double xxVel = file.readDouble();
                double yyVel = file.readDouble();
                double mass = file.readDouble();
                String imgFileName = file.readString();
                AllPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
            }

            return AllPlanets;
        }
        else
        {
            System.out.println("File name is invalid!");
            return null;
        }
    }

    public static void main(String[] args) {

        /** load data */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] AllPlanets = readPlanets(filename);
        int TotalNum = AllPlanets.length;

        /** draw the windows */
        /* Sets up the universe*/
        StdDraw.setScale(-radius, radius);
        /* Clears the drawing window. */
        StdDraw.clear();
        /* Enables double buffering for animation. To update, call show()*/
        StdDraw.enableDoubleBuffering();
        //create array for xForce and yForce
        double[] xForces = new double[TotalNum];
        double[] yForces = new double[TotalNum];
        for (double time = 0; time < T; time = time + dt) {

            // calculate net force for each planet
            for(int i = 0; i < TotalNum; i++) {
                Planet p = AllPlanets[i];
                xForces[i] = p.calcNetForceExertedByX(AllPlanets);
                yForces[i] = p.calcNetForceExertedByY(AllPlanets);
            }

            // update the position and velocity for each planet
            for(int i = 0; i < TotalNum; i++) {
                Planet p = AllPlanets[i];
                p.update(dt, xForces[i], yForces[i]);
            }

            /* Draw the background*/
            StdDraw.picture(0,0,"images/starfield.jpg");
            /* Draw all planets. */
            for(Planet p : AllPlanets) {
                p.draw();
            }
            /* Shows the drawing to the screen, and waits 10 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
        }

        /** Print out the final status of simulation */
        StdOut.printf("%d\n", AllPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < AllPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    AllPlanets[i].xxPos, AllPlanets[i].yyPos, AllPlanets[i].xxVel,
                    AllPlanets[i].yyVel, AllPlanets[i].mass, AllPlanets[i].imgFileName);
        }


    }
}
