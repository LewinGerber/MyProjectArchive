package ch.bbw.webshop;

import ch.bbw.webshop.entity.DBManager;
import ch.bbw.webshop.entity.Product;
import ch.bbw.webshop.entity.repository.ProductRepository;
import ch.bbw.webshop.security.JwtTokenUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Lewin Gerber
 * @version 21.11.2020
 * webshop
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class WebshopController {

    @Autowired
    private WebService webService;

    @Autowired
    private DBManager DBMANAGER;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /*
        RETURN ALL THE PRODUCTS
    */
    @RequestMapping(method = RequestMethod.GET, value="/getProducts")
    public List<Product> getProducts() {
        return  DBMANAGER.findAllProducts();
    }

    /*
        GET CART CODE
    */
    @RequestMapping(
            method = RequestMethod.GET,
            value= "/getUserCart",
            produces = "application/json"
    )
    public ResponseEntity<?> getUserCart(
            @RequestHeader(value = "Authorization", defaultValue = "") String token){
        if(jwtTokenUtil.checkTokenHeader(token)) {
            String cartCode = webService.getCartCode(jwtTokenUtil.getEmailFromToken(token.substring(7)));
            return new ResponseEntity<>(cartCode, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.OK);
    }

    /*
        SAVE THE UPDATED CART
    */
    @RequestMapping(
            method = RequestMethod.POST,
            value= "/updateUserCart"
    )
    public void updateUserCart(
            @RequestBody String cartCode,
            @RequestHeader(value = "Authorization", defaultValue = "") String token) throws UnsupportedEncodingException {
        webService.saveCartCode(
                jwtTokenUtil.getEmailFromToken(token.substring(7)),
                (cartCode.substring(0, cartCode.length()-1)));
    }

}
