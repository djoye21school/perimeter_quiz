import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    private double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }
    
    private int getNumPoints (Shape s) {
        int i = 0;
        for(Point iter : s.getPoints()){
            i++;
        }
        return i;
    }
    
    private double getAverageLength(Shape s) {
        return getPerimeter(s) / getNumPoints(s);
    }

    private double getLargestSide(Shape s) {
        double max = 0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            if(max < prevPt.distance(currPt))
                max = prevPt.distance(currPt);
        }
        return max;
    }

    private double getLargestX(Shape s) {
        double maxX = 0.0;
        int flag = 0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            if (flag == 0){
                maxX = currPt.getX();
                flag = 1;
            }
            else if (flag == 1 && maxX < currPt.getX()){
                maxX = currPt.getX();
            }
        }
        return maxX;
    }

    private double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double max_length = 0.0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double curr_length = getPerimeter(s);
            if (curr_length > max_length)
                max_length = curr_length;
        }
        return max_length;
    }

    private String getFileWithLargestPerimeter() {
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        double maxLength = 0.0;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currLength = getPerimeter(s);
            if (currLength > maxLength){
                maxLength = currLength;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int i = getNumPoints(s);
        double avg = getAverageLength(s);
        double max = getLargestSide(s);
        double max_x = getLargestX(s);
                
        System.out.println("perimeter = " + length);
        System.out.println("num " + i);
        System.out.println("avg " + avg);
        System.out.println("max " + max);
        System.out.println("max_x " + max_x);
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("mult_perimeter " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("file " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
       
    }
}
