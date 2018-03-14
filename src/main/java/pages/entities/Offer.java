package pages.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.WebElement;

@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = {"soledOutOffer", "image", "button"})
public class Offer {

    private final boolean soledOutOffer;
    private final String hotelName;
    private final String hotelLocation;
    private final WebElement image;
    private final WebElement button;

}
