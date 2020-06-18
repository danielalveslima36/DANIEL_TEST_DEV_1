package backend

class Company {
    long id;
    String name;
    String segment;

    static hasMany = [stocks: Stock]

    static constraints = {
        id unique: true
        name size: 1..150
        segment size: 1..150
        stocks nullable: true, blank: true
    }

    static mapping = {
        stocks cascade: 'all'
        table: 'company'
        version false

    }


    @Override
    public String toString() {
        return "Company{" +
                "stocks=" + stocks +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", segment='" + segment + '\'' +
                '}';
    }
}
