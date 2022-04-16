
<p align="center">
  <img src="/doc/webshop_faded.png" />
</p>


# Lorem Ipsum
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud        exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla   pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.

## Frontend
Lorem Ipsum ist den meisten bekannt. Dieser Webshop verkauft verschiedene Lorem Ipsum. Diese Idee ist entstanden, da es auf verschiedensten Webseiten Lorem Ipsum Generatoren gibt, welche sich eignen um Füllertexte in ein Design zu integrieren. Nur bei dieser Projektarbeit ist es kein Füllertext sondern das Produkt.

### Design
Das Frontend ist möglichst minimalistisch aufgebaut. Es wird vor allem Schwarz und Weiss verwendet um einen Look von einem sehr simplen, fast zu schlichten Webshop zu erstellen. React.js wird mit MaterialUI teils ergänzt, grundsätzlich wird aber auf herkömmliche HTML und CSS Tags und Styles gesetzt.

### Technische Umsezung mit React
Der Webshop ist eine Single-Page Applikation. Aus diesem Grund gibt es eine Datei, welche alle Informationen verwaltet und in Form von Lifted-State an die Unter-Komponenten Verteilt.

Um diese in einer übersichtlichen Weise zu organisieren wurden die States so definiert:
```javascript
    //list of all the available products (retrieved from db)
    const [products, setProducts] = useState([]);
    //list of all the items in the cart (if logged in also compared to db)
    const [cartProducts, setCartProducts] = useState([]);
    //cart code (global for db)
    const [cartCode, setCartCode] = useState("-"); 
```

Es gibt dabei 3 Nennenswerte Komponenten, welche alle mit diesen States arbeiten:
  * Produktansicht (ProductGrid)
  * Der Einkaufswagen (Cart)
  * Das Userlogin
```javascript
<div>
  <div id="spliter">
      <div id="product-view">
      
            //PRODUKTE KOMPONENTE
            <ProductGrid
              products={products}
              addCartProduct={(id) => addCartProduct(id)}
            />
      </div>
      <div id="menu-view">
        <div id="title-area"></div>
        
        //EINKAUFSWAGEN KOMPONENTE
        <Cart
          cartProducts={cartProducts}
          increaseQuantity={(id) => increaseCartProductQuantity(id)}
          decreaseQuantity={(id) => decreaseCartProductQuantity(id)}
          removeCartProduct={(id) => removeCartProduct(id)}
        />
        
        //LOGIN KOMPONENTE
        <Login
          loadCartCode={() => loadCartCode()}
          cartCode={cartCode}
        />
    </div>
  </div>
</div>
```


Die Produkte an sich werden dabei in einfache Divs gepackt. Die Komponente eines Produktes enthält zudem ein Popover, welches von MaterialUI ist und angezeigt wird, sobald das Produkt angeklickt wird. In ihm sind weitere Informationen zum Produkt enthalten. Der Code für diese Komponente sieht schlussendlich so aus:

```javascript
const Product = (props) => {
    const [open, setOpen] = useState(false);
    /*
        ////////////////////////////////
        toggle open popover
    */
    function toggleOpen() {
        setOpen(!open);
    }

    return (
        <div>
            <Popover
                open={open}
                style={{ width: '100rem !important' }}
                onClose={() => toggleOpen()}
                anchorOrigin={{
                    vertical: 'top',
                    //change depending on position of element
                    horizontal: 'left',
                }}
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right',
                }}
            >
                The content of the Popover.
            </Popover>

            <div>
                <img onClick={() => toggleOpen()} 
                    style={{ width: 50, height: 'auto' }} 
                    src={"data:image/png;base64," + props.image} 
                    alt={"product image: " +props.name}
                 />
                {'name:' + props.name}
                {' id:' + props.id}
                {' price:' + props.price}
            </div>
        </div>
    );
}
```

Das Popover stellt dabei eine besondere Herausforderung dar, da dies immer so platziert werden muss, dass alles Sichtbar ist. Die MaterialUI Dokumentation hilft
dabei aber sehr, da diese interaktive Beispiele hat. So für dieses Popover beispielsweise hier: [Popover interaktives Beispiel](https://material-ui.com/components/popover/#anchor-playground).



---
## Backend
Im Backend gibt es mehrere Schwierigkeiten welche gelöst werden müssen. In den folgenden Abschnitten wird nun zuerst angeschaut wie genau die Datenbank umgesetzt wurde und danach wie die User verwaltet werden.

### Datenbank
Für die Datenbank wurde auf eine MYSQL Datenbank gesetzt (Der SQL Dump lässt sich im entsprechenden Ordner oder [hier](https://github.com/LewinGerber/LoremIpsum/tree/main/SQL-DUMP) finden).
Diese wird mit Spring Data und JPA mit dem Backend verbunden. Es gibt deswegen im Backend ein Package mit der Bezeichnung Entity. In ihm werden alle Tabellen (Entitäten) in form von JPA-Klassen gespeichert. Zu jeder dieser Klasse gehört ein CrudRepository-Interface, welches vorgegebene Funktionen zur Interaktion haben, welche dann in der “DBMANAGER” Klasse gesammelt und als Service zur Verfügung gestellt werden.

#### Bilder
Die erste Schwierigkeit, welche sich stellt ist das Speichern von Bilder. In jedem Webshop ist es notwendig Bilder zu haben. Die simpelste Lösung wäre, die Bilder in einem Ordner im Frontend zu speichern. Diese Lösung erlaubt es allerdings nicht einfach Bilder zu ersetzen oder anzupassen. Um dieses Problem zu lösen wurde zuerst eine Lösung implementiert, welche einen Ordner in Spring-Boot speichert und diesen dann mit der ID jeweils einliest. Um die Bilder zu einem Byte Array zu machen wurde eine Methode entwickelt, welche am Ende so ausgesehen hat:

```Java
//MAPPING IM CONTROLLER
@RequestMapping(
            method = RequestMethod.GET,
            value= "/getThumbnail/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
public byte[] getThumbnailE(@PathVariable int id) throws IOException {
    System.out.println(webService.retrieveThumbnail(1));
    return webService.retrieveThumbnail(id);
}
```
```Java
//METHODEN IM SERVICE
public byte[] retrieveThumbnail(int id) throws IOException {
    for(File image : imageDir.listFiles()) {
        if(image.getName().substring(image.getName().length()-6).substring(0, 2).equals("t" + id))
            return Files.readAllBytes(Paths.get(image.getPath()));
        }
    return null;
}

public File eztri(int id) throws IOException {
    for(File image : imageDir.listFiles()) {
        if(image.getName().substring(image.getName().length()-6).substring(0, 2).equals("t" + id))
            return image;
        }
    return null;
}

```

Diese Lösung löst allerdings das Problem oberhalb immer noch nicht komplett, da die Bilder immer noch an einem separaten Ort gespeichert sind und besonders benannt werden müssen (z.B ‘bildName_T1’). Um dies zu lösen gibt es schlussendlich nur eine Lösung und die ist, die Bilder direkt in die DB zu speichern.
Um dies zu machen, haben wir zwei Punkte beachtet:
    * in Byte und als Base64 codiert gespeichert
    * es werden nur weboptimierte Bilder eingefügt

Mit dieser Lösung war es nun möglich die Bilder immer ohne Zwischenschritte direkt einem Produkt zu zuordnen. Da React Base64 decodieren kann, war das Verwenden dieser Daten extrem einfach:

```JavaScript
    //so sieht der vereinfachte Tag für ein Bild aus 
    //(props.image = Daten direkt aus der DB über den Rest-Controller)
    <img src={"data:image/png;base64," + props.image} />
```

Wenn die Bilder keine PNGs wären könnte das Präfix auch direkt in die Datenbank gespeichert werden.

#### Cart Code
Die zweite Schwierigkeit mit der Datenbank war das Speichern des Einkaufswagens. Allgemein betrachtet könnte das Cart in einer Zwischentabelle gespeichert werden. Wir haben uns allerdings für eine andere Lösung entschieden, weil sich eine Zwischentabelle nicht angemessen angefühlt hat, da nur wenige ID’s gespeichert werden mussten. 
Als Alternative wird nun beim Benutzer als Attribute ein extra entwickelter Code gespeichert.
Dieser enthält einerseits die ID des Produkts und andererseits die Anzahl des jeweiligen Produktes.
Ein Beispiel für diesen Code sieht man hier:

```JavaScript
    //die 1 steht für das Produkt mit der ID = 1 und die Menge ist 3
    -1A3-
```

Die ID und die Anzahl wird dabei durch ein ‘A’ unterteilt. Dies wird so gemacht, um diese beiden Zahlen auseinander zu halten. Es wird dabei ein ‘A’ verwendet, da dieses in den meisten Codierungen gleich ist. Würde man beispielsweise ein ‘#’ verwenden, wäre die in ASCII nicht gleich wie bei Unicode. Der Bindestrich markiert dabei das Ende und auch den Anfang eines Produktes.

Die Implementation dieses Codes ist grundsätzlich keine grosse Schwierigkeit, aber es hat sich herausgestellt, dass es gar nicht so einfach war diesen Code korrekt zu implementieren und es gibt bis jetzt immer noch Fehler. Trotzdem gibt es im React Funktionen, welche die grundsätzliche Idee hinter diesem Code umsetzen: 

```JavaScript
    /*
        ////////////////////////////////////////////////////////////////
        cart code encoder - saves the code to localstorage and returns it
    */
    function encodeCart(cartProducts) {
        let cartCode = "-";
        cartProducts.map(cartProduct => {
            //add the encoded version of every product
            if (cartProduct !== undefined)
                cartCode += cartProduct.id + 'A' + cartProduct.quantity + '-';
        });
        return cartCode;
    }   
```


```JavaScript
    /*
        ////////////////////////////////////////////////////////////////
        cart code decoder - and saves the data in array
    */
    async function decodeCartCode(code) {
        let elements = code.split('-');
        try {
            //call to backend
            const result = await axios.get('/api/getProducts');
            elements.map(element => {
                if (element !== "" && element !== undefined) {
                    //always only 2 elements (0 and 1)
                    let productParts = element.split('A');
                    result.data.map(product => {
                        if (product.id == productParts[0]) {
                            let tempProduct = product;
                            tempProduct.quantity = productParts[1];
                            initiateProduct(tempProduct);
                        }
                    });
                }
            });
        } catch (e) { }
    }
```

### Benutzer und Token
Die letzte grosse Challenge vom Backend waren die Benutzer. Mit JWT werden diese Authentifiziert. Da diese Umsetzung eher komplex ist, war es von Vorteil, dass eine Ähnliche Implementation schon in einem anderen Modul vorgenommen wurde, weswegen der grösste Teil des Codes übernommen werden konnte. Im Backend gibt es nun einen Ordner, der ‘Security’ heisst. In ihm werden alle wichtigen Klassen für die Erstellung und die Entzifferung gesammelt. Der ‘AuthenticationController’ übernimmt dabei die wichtigsten Mappings für die Nutzer, um die Applikation an sich getrennt zu halten.
Ein erstellter Token sieht nun so aus wie unten dargestellt und wird dem gezeigten Kommentar in den Localstorage gespeichert:

```JavaScript
    //  JWT - Lorem Ipsum TOKEN (LIT)
    Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb290IiwiZXhwYXQiOjE2MTA4Nzc4MzR9...
```

```JavaScript
    //  Speichern des Tokens mit React.js
    localStorage.setItem("lit", code);
```
# Schlusswort
Auch wenn am Ende von diesem Projekt der Webshop nicht einwandfrei funktioniert, war das Erlernen von React.js und das einbinden von Spring Boot eine spannenden Herausforderung. Wir haben viel darüber gelernt wie Web-Applikationen wirklich funktionieren und wie unterstützend ein Framework oder eine Library sein können.

