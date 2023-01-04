import java.util.Comparator;

class AlphabeticalComparator implements Comparator<CV> {
    @Override
    public int compare(CV cv1, CV cv2) {
        return cv1.getName().compareToIgnoreCase(cv2.getName());
    }
}
