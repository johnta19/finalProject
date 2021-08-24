package api.data.provider;

import api.pojo.Bookingdates;
import api.pojo.CreateBookingPojo;
import api.pojo.CreateTokenPojo;
import api.pojo.PartialUpdateBookingPojo;

import static api.constants.Credentials.LOGIN;
import static api.constants.Credentials.PASSWORD;

public class Builder {
    public CreateTokenPojo createToken() {
        CreateTokenPojo createToken =  CreateTokenPojo.builder()
                .username(LOGIN)
                .password(PASSWORD)
                .build();
        return createToken;
    }

    public Bookingdates bookingdates() {
        Bookingdates  bookingdates = Bookingdates.builder()
                .checkin("2018-01-01")
                .checkout("2019-01-01")
                .build();
        return bookingdates;
    }

    public CreateBookingPojo createBooking() {
        CreateBookingPojo createBooking = CreateBookingPojo.builder()
                .firstname("Jim")
                .lastname("Brown")
                .totalprice(111)
                .depositpaid(true)
                .bookingdates(bookingdates())
                .additionalneeds("Breakfast")
                .build();
        return createBooking;
    }

    public PartialUpdateBookingPojo updateAuthor() {
        PartialUpdateBookingPojo updateAuthor = PartialUpdateBookingPojo.builder()
                .firstname("James")
                .lastname("Bond")
                .build();
        return updateAuthor;
    }
}
