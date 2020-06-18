package backend

import java.time.LocalDate
import java.time.LocalDateTime

class Stock {
    long id
    Double price
    Date priceDate
    static belongsTo = [company: Company]


    static constraints = {
        id unique: true;
        price blank: false
        priceDate blank: false
    }

    static mapping = {
        table: 'company'
        version false
    }


    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", price=" + price +
                ", priceDate=" + priceDate +
                '}';
    }
}
