public class University implements Comparable<University> {
    private String name;
    private String city;
    private String url;

    public University (String name, String city, String url) {
        this.name = name;
        this.city = city;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("Name: %s   City: %s   Web Address: %s", name, city, url);
    }

    public int compareTo(University other) {
        int result = 0;
        result = this.name.compareToIgnoreCase(other.name);
        if(result == 0)
            result = this.city.compareToIgnoreCase(other.city);
        return result;
    }
}
