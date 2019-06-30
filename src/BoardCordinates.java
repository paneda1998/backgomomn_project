//
public class BoardCordinates {
    int xcordinateBegin;
    int xcordinateEnd;
    int xcordinateMean = (xcordinateBegin + xcordinateEnd)/2;
    boolean top;

    public BoardCordinates(int xcordinateBegin, int xcordinateEnd, boolean top) {
        this.xcordinateBegin = xcordinateBegin;
        this.xcordinateEnd = xcordinateEnd;
        this.top = top;
        this.xcordinateMean = (this.xcordinateBegin + this.xcordinateEnd)/2;
    }
}