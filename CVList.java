import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CVList {
    private List<CV> cvs;

    public CVList() {
        cvs = new ArrayList<>();
    }

    public void addCV(CV cv) {
        cvs.add(cv);
    }

    public void removeCV(CV cv) {
        cvs.remove(cv);
    }

    public CV getCV(String name) {
        for (CV cv : cvs) {
            if (cv.getName().equals(name)) {
                return cv;
            }
        }
        return null;
    }

    public List<CV> search(String query) {
        List<CV> searchResults = new ArrayList<>();
        for (CV cv : cvs) {
            if (cv.matchesQuery(query)) {
                searchResults.add(cv);
            }
        }
        return searchResults;
    }

    public void sortAlphabetically() {
        Collections.sort(cvs, new AlphabeticalComparator());
    }

    public List<CV> getCVs() {
        return cvs;
    }
}

