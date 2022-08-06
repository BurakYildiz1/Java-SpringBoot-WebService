Web Service Api Dökümantasyonu


Bu döküman Web Servis Api sisteminin çalışma yapısını anlatmak için oluşturulmuştur.
Web Servis Api çift taraflı çalışmaktadır. Api ile hem veri yazılabilir
hemde siteden veri çekilebilir.

Web Servis Api ile aşağıdaki listede yer alan veriler üzerinde çalışabilirsiniz.

Usersdata
    int id;
    String fullName;
    String eMail;
    String password;
    boolean isActive;

ProductData
    int id;
    String name;
    double price;
    String imageUrl;
    int categoryId;
    boolean isActive;

CategoryData
    int id;
    String name;
    boolean isActive;

Not: Web-Servis tarafında isActive kontrolü yaptırılıp sadece aktif olan ürünler 
dönmektedir


##URLs.

#Login
Parametreler(post)eMail,password 

http://localhost:8080/users/login

#Register
Parametreler(post)fullname,eMail,password

http://localhost:8080/users/register

#Products

1-Ürün listele
Parametreler(get)
http://localhost:8080/products/getProducts

2-Kategori ürünü listele
Parametreler(get)categoryId

http://localhost:8080/products/listProducts?categoryId=2

#Category

http://localhost:8080/categories/getCategories








