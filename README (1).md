## Mikro Finans Takip Sistemi - Spring Boot Edition
Mikro finans App, Java Spring Boot ile geliştirilmiş bir backend uygulamasıdır. Bu proje, web servisleri oluşturma, RESTful API geliştirme, Spring Boot ile veritabanı işlemleri ve hata yönetimi gibi konuları içermektedir.

## Özellikler
- Spring Boot: RESTful API geliştirmek için kullanılmıştır.
- Veritabanı Entegrasyonu: Spring Data JPA kullanılarak CRUD işlemleri gösterilmiştir.
- Hata Yönetimi: Uygulama genelinde etkili hata yönetimi ve istisna yakalama mekanizmaları uygulanmıştır.
- Transaction Yönetimi: Veritabanı işlemlerinde transaction yönetimi sağlanmıştır.

## Başlangıç
## Gereksinimler
- Java 21
- Maven 3.8
- Docker
- IntelliJ IDEA veya başka bir IDE
- MySQL veya uyumlu başka bir SQL veritabanı

## Kurulum
1. Projeyi klonlayın:https://github.com/eshms/KizkardesBitime
2. Veritabanını yapılandırın: src/main/resources klasöründeki application.properties dosyasını veritabanı bilgilerinize göre güncelleyin.
3. Projeyi Maven ile derleyin: mvn clean install
4. Spring Boot uygulamasını çalıştırın: mvn spring-boot:run
5. Docker ile çalıştırma: Uygulamayı Docker ile çalıştırmak için, projenin kök dizininde bulunan docker-compose.yml dosyasını kullanabilirsiniz

## Kullanılan Teknolojiler
1. Java 21: Backend mantığı için kullanılan ana programlama dili.
2. Spring Boot: RESTful servislerin hızlı geliştirilmesi için kullanıldı.
3. Maven: Proje bağımlılık yönetimi ve derleme işlemleri.
4. Docker: Uygulamayı ve MySQL veritabanını kapsayıcı olarak çalıştırmak için kullanılır.
5. MySQL: Veritabanı yönetim sistemi olarak kullanılmıştır.
