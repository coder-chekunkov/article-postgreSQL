### :book: PostgreSQL, Spring Framework и Android для начинающего разработчика.

Данная статья была написана [coder-chekunkov](https://github.com/coder-chekunkov) и [uttsvlad](https://github.com/uttsvlad) для начинающих разработчиков. Темы, которые будут затронуты в данной статье: **PostgreSQL**, **Spring Framework** и **Android**. <br/>
Статья опубликована на информационном ресурсе "Habr" - ссылка.

---

Здравствуй, дорогой читатель. Каждый разработчик, независимо от его специальности, сталкивался (или столкнётся во время своей профессиональной карьеры) с задачей, в которой необходимо разработать проект, имеющий базу данных, серверную часть и конечный продукт, взаимодействующий с пользователем. Данная статья поможет новичку разобраться с данной задачей.

В статье будут затронуты такие важные темы, как теория баз данных, реляционная база данных **PostgreSQL**, **Spring Framework** и **Android разработка**. Также будет рассмотрен базовый, не очень сложный пример, который поможет разобраться во всех этих темах и "потрогать" их руками.

Статья предназначена для начинающего разработчика, но имеющего базовые знания о разработке программного обеспечения и языках программирования Java и Kotlin.

Все материалы и исходный код можно найти [здесь](https://github.com/coder-chekunkov/PostgreSQL-Article).

### Что должно получиться?

В конечном итоге должен получиться **маленький pet-проект**, с мобильным приложением, серверной частью и базой данных. 

Основная тема приложения: интернет-магазин, который продаёт какой-то товар.
У конечного пользователя должна быть возможность зарегистрироваться и авторизоваться в интернет-магазине, редактировать свои персональные данные, выбирать и заказывать товар.

*[...gif-изображение с конечным продуктом...]*

### Теория баз данных

Перед тем как изучать PostgreSQL, Spring Framework и Android необходимо **разобраться с основными понятиями баз данных**.

`База данных` - организованная коллекция данных, которая хранится в компьютерной системе и используется для эффективного хранения, управления и доступа к информации. База данных содержит структурированные данные, такие как числа, текст, изображения или другие типы информации, и обеспечивает методы для добавления, изменения, удаления и извлечения данных.

`СУБД (Система Управления Базами Данных)` - программное обеспечение, предназначенное для создания, управления и обработки баз данных. СУБД предоставляет интерфейсы и функциональность, позволяющие пользователям определить структуру данных, добавлять, изменять, удалять и извлекать данные из базы данных, а также выполнять другие операции, связанные с обработкой данных. 

`Сущность базы данных` - объект или объектная модель, который представляет определенный тип данных или информацию, хранящуюся в базе данных. Сущность базы данных также может называться "таблицей" или "классом" в различных моделях баз данных.

`Атрибут базы данных` - конкретная характеристика или свойство, которое определяет определенный аспект сущности базы данных, такой как сущность, таблица или класс. Атрибуты представляют собой конкретные данные, которые хранятся в базе данных.

`ER-диаграмма` - модель данных, позволяющая описывать концептуальные схемы предметной области. ER-модель используется при высокоуровневом проектировании баз данных. С её помощью можно выделить ключевые сущности и обозначить связи, которые могут устанавливаться между этими сущностями.

`Первичный ключ` - уникальный идентификатор, который однозначно идентифицирует каждую запись в таблице базы данных. Он используется для уникальной идентификации записей в таблице и обеспечивает уникальность данных в столбце или наборе столбцов.

`Внешний ключ` - атрибут или набор атрибутов в таблице, который ссылается на первичный ключ (или уникальный ключ) в другой таблице. Он используется для установления связей между таблицами в реляционной базе данных. Внешний ключ служит для определения отношений между записями в разных таблицах и обеспечивает целостность данных.

`Транзакция` - логическая операция или последовательность операций, выполняемых в базе данных, которые образуют единую и неделимую единицу работы. Транзакция может включать операции чтения, записи или модификации данных в базе данных. Главной особенностью транзакции является ее атомарность, то есть она либо выполняется полностью, либо не выполняется совсем.

`Нормализация` - процесс организации данных в базе данных с целью устранения избыточности и аномалий данных, чтобы обеспечить эффективное хранение, манипуляцию и поддержку целостности данных.

`Связь` - способ, с помощью которого данные в различных таблицах базы данных могут быть связаны и взаимодействовать друг с другом. Связи используются для определения отношений между таблицами и обеспечивают эффективную организацию и связность данных в базе данных.

Существует несколько типов связей в реляционных базах данных, таких как:


| Связь | Пояснение | Пример |
| -------- | -------- | -------- |
| Один-к-одному (One-to-One)     |  Один объект в одной таблице связан с одним объектом в другой таблице.     | Таблица "Сотрудники" может быть связана с таблицей "Паспортные данные" с помощью уникального идентификатора сотрудника.     |
| Один-ко-многим (One-to-Many)     | Один объект в одной таблице связан с несколькими объектами в другой таблице.    | Таблица "Отделы" может быть связана с таблицей "Сотрудники", где один отдел может иметь несколько сотрудников, используя общий идентификатор отдела.     |
| Многие-ко-многим (Many-to-Many)    | Множество объектов в одной таблице связано с множеством объектов в другой таблице. Для реализации такой связи требуется дополнительная таблица-связь, которая содержит связи между объектами двух таблиц.| Таблица "Студенты" может быть связана с таблицей "Курсы" через таблицу-связь "Расписание", чтобы отслеживать связи между студентами и курсами.   |


Источники, которые могут **расширить кругозор** по теории баз данных можно найти [здесь](https://metanit.com/sql/), [здесь](https://habr.com/ru/articles/555760/) и [здесь](https://site-do.ru/db/db1.php).

### PostgreSQL и создание базы данных

Теперь следует поговорить о PostgreSQL.

`PostgreSQL` - это свободная объектно-реляционная система управления базами данных (СУБД), которая является одной из наиболее мощных и распространенных СУБД в мире. 

PostgreSQL предоставляет множество возможностей, включая поддержку **SQL** (Structured Query Language), транзакции с поддержкой **ACID** (атомарность, согласованность, изолированность, долговечность), индексы, хранимые процедуры, триггеры и многое другое. Она также обладает **высокой степенью надежности**, масштабируемости и производительности, что делает ее популярным выбором для различных приложений, включая веб-сайты, системы управления контентом, финансовые приложения и т.д. 

В первую очередь, при разработке базы данных, необходимо **составить ER-диаграмму**, которая наглядно отобразит будущую систему.

[image-DB-001]

| Таблица | Пояснение | 
| -------- | -------- | 
|USER|Содержит данные пользователей, такие как ФИО, email и пароль, хранящийся в зашифрованном виде|
| PRODUCT|Хранит данные продуктов: ссылка на изображение, название, цена|
|CART|Сопоставляет пользователя с его корзиной и продуктами в ней. Вспомогательной является таблица CART_PRODUCTS|

Ниже приведен **SQL-скрипт, который создаёт базу данных**:

```sql
create table _user
(
    id          bigserial not null,
    email       varchar(255),
    first_name  varchar(255),
    last_name   varchar(255),
    middle_name varchar(255),
    password    varchar(255),
    primary key (id)
)

create table cart
(
    id      bigserial not null,
    user_id bigint,
    primary key (id)
)

create table cart_products
(
    cart_id     bigint not null,
    products_id bigint not null
)

create table product
(
    id    bigserial not null,
    image varchar(255),
    name  varchar(255),
    price float(53),
    primary key (id)
)

alter table if exists cart_products
    add constraint UK_3kg5kx19f8oy0lo76hdhc1uw1 unique (products_id)

alter table if exists cart
    add constraint FKil7wc86wc3xs4je2ghfenn854 foreign key (user_id) references _user

alter table if exists cart_products
    add constraint FKhyhnx21758m3wmbi4ps96m0yw foreign key (products_id) references product

alter table if exists cart_products
    add constraint FKnlhjc091rdu9k5c8u9xwp280w foreign key (cart_id) references cart
```

Также привём SQL-скрипт, который **заполняет базу данных тестовыми значениями**:

```sql
insert into product
values (default,
        'https://static.wikia.nocookie.net/fnaf-fanon-animatronics/images/4/40/%D0%91%D0%B0%D0%BD%D0%B0%D0%BD.png/revision/latest?cb=20190614113143&path-prefix=ru',
        'Banana', 179.90),
       (default,
        'https://m.dom-eda.com/uploads/images/catalog/item/86df51de21/c25c94fe96_1000.jpg',
        'Apple', 199.90),
       (default,
        'https://media.vprok.ru/products/x700/tn/u6/uyt2yc2e337ofqf7vsfm4cdomdxwu6tn.jpeg',
        'Strawberry', 250.00),
       (default,
        'https://foodcity.ru/storage/products/October2018/8No9uQ14ycYG7UluJEaM.jpg',
        'Pineapple', 419.90),
       (default,
        'https://eden-g.com/assets/images/products/1645/gridjpgbigest/qiwi-m.jpg',
        'Kiwi', 315.90),
       (default,
        'https://fruit-berries.ru/images/cms/thumbs/3a031f89ff2849f64a5233c90f77a8ac353f35bb/durian-800x1000_483_483_jpg_5_92.jpg',
        'Durian', 899.00),
       (default,
        'https://ecomarket.ru/imgs/products/13647/mango-sochnoe---600-g-1.1632501465.JPG',
        'Mango', 510.90)
```

### Spring Framework

`Spring Framework` - это популярный фреймворк для разработки приложений на языке Java. Он предоставляет различные инструменты и функции, которые упрощают разработку приложений, такие как управление зависимостями, инверсия управления, а также возможность использования различных модулей для реализации конкретной функциональности.

*Spring Framework* построен на основе паттерна **"Inversion of Control" (IoC)**, что позволяет сосредоточиться на разработке бизнес-логики приложения, а не заботиться о решении технических задач, связанных с управлением зависимостями и ресурсами. Кроме того, *Spring Framework* также поддерживает паттерн **"Aspect Oriented Programming" (AOP)**, который позволяет разделять общую функциональность на отдельные аспекты, чтобы упростить их использование и повторное использование.

*Spring Framework* включает в себя **множество модулей**, каждый из которых предназначен для решения конкретных задач, таких как веб-приложения, работа с базами данных, интеграция с другими приложениями и т.д. Эти модули могут использоваться как самостоятельно, так и в комбинации с другими модулями, что делает Spring Framework очень гибким и масштабируемым решением для различных типов приложений.

Составим "поэтапный план действий", которому будем придерживаться для создания backend'a приложения:

***1. Подключение зависимомстей.***

В качестве сборщика **следует использовать Gradle**, подробнее про работу с ним можно прочитать [здесь](https://spring.io/guides/gs/gradle/). 

Нам необходимо **дабавить следующие модули** Spring: *Data JPA*, *Web* и *Security*. Также, необходимо **подключить драйвер базы данных** PostgreSQL. 

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'com.auth0:java-jwt:4.4.0'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'org.postgresql:postgresql'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

***2. Создание сущностей и репозиториев.***

Необходимо **создать 3 класса сущностей**: User, Product и Cart, пометим их аннотацией `@Entity`. Про механизм ORM, который использует *Spring Data JPA* подробнее можно прочитать [здесь](https://practicum.yandex.ru/blog/hibernate-java/).

```java
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
}
```
```java
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String image;

}
```
```java
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToMany
    private List<Product> products;
}
```
Аналогичным образом **создадим репозитории**:
```java
public interface CartRepository extends JpaRepository<Cart, Long> {
}
```

*Spring Data* позволяет разработчику не беспокоиться над реализацией метода, который решает типичные задачи манипуляций данными (чтение, добавление, обновление и удаление). Например, мы хотим создать метод, который будет искать корзину по пользователю, для этого достаточно описать сигнатуру в интерфейсе **без последующей реализации**, например: 
```java
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
```

Для более глубокого понимания как работает Spring Data - рекомендую посмотреть данный [источник](https://www.youtube.com/watch?v=nwM7A4TwU3M&t=5s).

***3. Создание контроллеров.***

**Создадим контроллеры**, в которых происходит обработка входящих запросов. В качестве примера разберем UserController:
```java
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        User user = userService.findUser(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody UserEditDTO userEditDTO) {
        User user = userService.editUser(id, userEditDTO);
        return ResponseEntity.ok(user);
    }
}
```
В нем представлены 2 конечные точки: ***/users/{id}*** и ***/users/edit/{id}***.
Аннотация `@RestController` сообщает Spring, что ответы на запросы неоходимо возвращать в формате JSON. `@RequestMapping("/users")` позволяет использовать один префикс для всех запросов. `@GetMapping` и `@PatchMapping` сообщают о том, какие HTTP методы исопльзовать для запросов: GET и PATCH соответсвенно.

***4. Создание бизнес-логики.***
```java
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow();
    }

    public User editUser(Long id, UserEditDTO userEditDTO) {
        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(userEditDTO.firstName());
        user.setLastName(userEditDTO.lastName());
        user.setMiddleName(userEditDTO.middleName());

        return userRepository.save(user);
    }
}
```
В сервисе присутсвует 2 метода с простой логикой: поиск пользователя по ID, в котором используется метод репозитория `findById(Long id)` с реализацией, поставляемой *Spring Data* по умолчанию, а также `editUser(Long id, UserEditDTO userEditDTO)` ([что такое DTO](https://habr.com/ru/articles/513072/)), который обновляет ФИО пользователя в базе данных с помощью метода `save(User user) UserRepository`.

***5. Безопасность.***

Для того, чтобы запросы к нашему приложению могли приходить только от аутентифицированных пользователей, **используем модуль** Spring Security. Первым делом **создадим и настроим файл конфигурации** SecurityConfig:
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests().requestMatchers("/auth/**").permitAll()
                .anyRequest().hasRole("USER")
                .and().sessionManagement().disable()
                .logout().logoutUrl("/logout")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```
**Особое внимание** в коде выше стоит уделить созданию бина `filterChain`, в нем описывается основная логика работы Spring Security. В качестве принципа, по которому мы создаем этот бин, выступает паттерн [chain of responsibility](https://metanit.com/sharp/patterns/3.7.php). Так, запрос поступающий нашему приложению поэтапно проходит валидацию через каждый метод, например: с помощью `.requestMatchers("/auth/**").permitAll()` мы сообщаем Spring-у пропускать все запросы начинающиеся на ***/auth/*** без авторизации, нужно это для того, чтобы пользователи могли зарегистрироваться/авторизоваться в приложении. Строкой `.anyRequest().hasRole("USER")` мы описываем, что ко всем остальным эндпоинтам имеют доступ только авторизованные пользователи с ролью "USER" (в соответсвии с логикой нашего приложения, она выдается всем при регистрации). Также, стоит обратить внимание на строку `http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);`, в ней мы добавляем фильтр проверки валидности [JWT](https://habr.com/ru/articles/340146/) токена.
Тема безопасности в Spring приложении достойна отдельного обсуждения, чтобы не перегружать статью, оставлю ссылку на изучение модуля [Security](https://www.baeldung.com/security-spring), [JWT-аутентификация в Spring приложении](https://www.toptal.com/spring/spring-security-tutorial). 

### Android-приложение

После того, как была разработана и заполнена база данных, создан сервер - необходимо перейти к **созданию мобильного приложения**, с которым будет взаимодействовать конечный пользователь.

В данной статье не будут рассмотрены основы разработки приложения - верстка layot'ов, связь с классами и т.д. Основное внимание будет уделено библиотеке [Retrofit2](https://github.com/square/retrofit).

**Важное уточнение!** В данном приложении используется небольшое "ядро" с архитектурой MVVM. Данное "ядро" используется для быстрого и качественного расширения приложения, а также для удобства разработки приложения с шаблоном проектирования MVVM. Более детально с "ядром" можно ознакомиться [здесь](https://github.com/coder-chekunkov/MVVM-architecture).

> MVVM (Model-View-ViewModel) - паттерн проектирования для разработки пользовательского интерфейса, который помогает разделить приложение на три отдельных компонента: Model, View и viewModel. Более подробно - [здесь](https://itsobes.ru/AndroidSobes/chto-takoe-mvvm/).

`Retrofit2` - это библиотека для работы с сетевыми запросами в приложениях для Android. Она предоставляет удобный интерфейс для взаимодействия с API удаленных серверов с использованием HTTP-запросов.

С помощью данной библиотеки можно **определить интерфейс** для взаимодействия с сервером и **использовать аннотации** для указания конечной точки URL, параметров запроса, тела запроса и типа запроса (например, GET, POST, PUT и т.д.). Также библиотека может **автоматически преобразовывать** ответы в Java/Kotlin-объекты с помощью специализированных конвертеров.

Библиотека упрощает процесс работы с сетевыми запросами и позволяет сосредоточиться на более важных аспектах разработки приложения. Retrofit2 обладает хорошей производительностью и является одной из наиболее популярных библиотек для работы с сетью в Android-разработке.

В первую очередь **необходимо импортировать** саму библиотеку и JSON-конвертер, который будет преобразовывать JSON-файлы в data-классы. Для этого в файле *build.gradle* уровня приложения, в тэге *dependencies*:

```groovy
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

Также добавим **другие зависимости**, которые потребуются в процессе разработки:

```groovy
dependencies {
    ---
    // !!! - Retrofit 2 & GSON-converter
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    // !!! - Glide
    implementation 'com.github.bumptech.glide:glide:4.15.1'
    // !!! - Fragment Ktx
    implementation 'androidx.fragment:fragment-ktx:1.5.6'
    // !!! - Kotlin Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'
    // !!! - JWT decode
    implementation 'com.auth0.android:jwtdecode:2.0.2'
    ---
}
```

Для работы библиотеки **необходимы разрешения**, которые следует прописать в AndroidManifest:

```xml
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

Также, важно отметить, что в Retrofit2 огромное количество задач **реализовываются с помощью аннотаций**, о которых будет рассказано ниже. Вот самые основные:

* `@GET`, `@POST`, `@PUT`, `@DELETE`, `@PATCH` - используются для указания типа HTTP-запроса, который должен быть отправлен на сервер.
* `@Body` - используется для передачи тела запроса. Тело запроса может быть представлено объектом модели или строкой.
* `@Path` - используется для передачи динамических параметров запроса. Значение параметра будет извлечено из URL-адреса запроса.
* `@Url` - используется для указания URL-адреса запроса. Эта аннотация может использоваться вместо параметра *baseURL*, который указывается в *Retrofit.Builder()*.
* `@Header`, `@Headers` - используются для передачи заголовков запроса. *@Header* используется для передачи одного заголовка, а *@Headers* - для передачи нескольких заголовков.
* `@JsonAdapter` - используется для указания пользовательского адаптера для сериализации и десериализации объектов в JSON.

После того, как все организационные вопросы были рассмотрены, а задачи сделаны, следует перейти к связи приложения и сервера. **Начнем с авторизации**.

В первую очередь **создадим сущности** *AuthEntity* и *TokenEntity*. Первая сущность,  содержащая авторизационные данные, будет отправляться на сервер. Вторая сущность, содержащая два токена, будет приходить с сервера.

```kotlin
data class AuthEntity(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
)

data class TokenEntity(
    @SerializedName("accessToken") var accessToken: String? = null,
    @SerializedName("refreshToken") var refreshToken: String? = null
)
```

Для реализации авторизации **создадим интерфейс** *UserAPI*, в котором, с помощью аннотаций, пропишем метод, который будет связываться с эндпоинтом "auth/login". Данный метод, в качестве тела запроса будет получать сущность AuthEntity, а возвращать - TokenEntity:

```kotlin
interface UserAPI {

    @POST("auth/login")
    suspend fun authorization(@Body body: AuthEntity): TokenEntity

}
```

Теперь **создадим класс** *UserServerRepository*, который будет вызывать методы связи с сервером. Данный класс имеет один метод *signIn*, принимающий e-mail и пароль как авторизационные данные пользователя. 
Важно отметить - метод *signIn* будет **вызываться из корутины**, соответсвенно, данный метод помечен ключевым словом *suspend*. Также в данном методе переопределен Dispatcher для корректной работы приложения. Подробнее про корутины можно почитать [здесь](https://metanit.com/kotlin/tutorial/8.1.php) и [здесь](https://kotlinlang.org/docs/coroutines-overview.html).

```kotlin
class UserServerRepository(retrofit: Retrofit) : UserRepository {

    private val userAPI = retrofit.create(UserAPI::class.java)

    override suspend fun signIn(email: String, password: String): TokenEntity {
        return withContext(Dispatchers.IO) {
            val authEntity = AuthEntity(email, password)

            return@withContext userAPI.authorization(authEntity)
        }
    }

}
```

В *AuthViewModel* **создадим метод** *authorization*, который будет принимать e-mail и пароль от пользователя со стороны фрагмента. Данный метод запускает новую корутину и вызывает метод *signIn* из UserServerRepository. 
В случае, если данные (два токена) будут **получены успешно**, то токены сохраняться в хранилище устройства, а после будет запущен основной экран.

```kotlin
    fun authorization(email: String, password: String) {
        viewModelScope.launch() {
            try {
                val tokenEntity = userRepository.signIn(email, password)
                tokenService.setTokens(tokenEntity)

                launchMainScreen()
            } catch (exception: Exception) {
                Log.d("Auth Error", exception.toString())
            }
        }
    }
```

Аналогично авторизации реализовывается регистрация. Пусть это будет **домашнем заданием**, ведь если вставлять каждый раз один и тот же код - статья бы стала слишком большой. Но, все же, если реализовать самому не получается - все исходники [здесь](https://github.com/coder-chekunkov/PostgreSQL-Article/tree/main/app).

Рассмотрим еще один запрос к серверу - получение персональных данных пользователя. В данном запросе необходимо **передать в параметре запроса** уникальный номер пользователя и провести авторизацию с помощью JWT. Создадим метод:

```kotlin
    @GET("users/{user_id}")
    suspend fun getUserPersonalData(
        @Header("Authorization") accessToken: String,
        @Path("user_id") userId: Long
    ): PersonalDataEntity
```

В параметрах данного метода находятся две переменные **с соответсвующими аннотациями**. Как уже было сказано, аннотация *@Header* используются для передачи заголовков запроса, в данном случае - авторизация. Аннотация *@Path* используется для передачи id пользователя в URL (*например, если у пользователя id = 7, то получаем путь: ".../users/7"*).

Аналогично сущностям авторизации и регистрации **создадим сущность** с персональными данными пользователя:

```kotlin
data class PersonalDataEntity(
    @SerializedName("id") var id: Long? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("middleName") var middleName: String? = null
) 
```

Идентично регистрации и авторизации **получим данные о пользователе** из userRepository, в котором создадим метод *getPersonalData*, принимающий токен и уникальный номер:

```kotlin
    override suspend fun getPersonalData(accessToken: String, userId: Long): PersonalDataEntity {
        return withContext(Dispatchers.IO) {
            return@withContext userAPI.getUserPersonalData("Bearer $accessToken", userId)
        }
    }
```

**Обратимся к этому методу** из *PersonalDataViewModel*. Функция во ViewModel создает новую корутину, в которой вытаскивает из хранилища устройства JWT, расшифровывает его (библиотека "JWT-Decode") и отправляет в репозиторий:

```kotlin
    private val _personalData = MutableStateFlow(PersonalDataEntity.emptyPersonalDataEntity)
    val personalData: StateFlow<PersonalDataEntity> = _personalData

    fun getPersonalData() {
        viewModelScope.launch {
            try {
                val accessToken = tokenService.getAccessToken()!!
                val jwt = JWT(accessToken)
                val id = jwt.getClaim("id").asString()?.toLong() ?: -1

                val personalDataEntity = userRepository.getPersonalData(accessToken, id)
                _personalData.value = personalDataEntity
            } catch (exception: Exception) {
                Log.d("PersonalData Error", exception.toString())
            }
        }
    }
```

Я надеюсь, основная концепция работы библиотеки понятна. Далее в данной статье будут приводиться только сущности, которые приходят с сервера, API-методы и методы репозиториев. 

### Заключение

В данной статье было затронуто огромное количество важных тем, с которыми должен быть знаком каждый разработчик, независимо от его специальности. 
Естественно, это **далеко не все**, что позволяют делать PostgreSQL, Spring Framework, Retrofit2, но лишь основы. Если Вы хотите сильнее углубиться в эти темы, то изучите следующие источники, которые могут помочь Вам:
* Metanit - Руководство по PostgreSQL - [ссылка](https://metanit.com/sql/postgresql/).
* Что такое База Данных (БД) - [ссылка](https://habr.com/ru/articles/555760/).
* Metanit - Программирование под Android - [ссылка](https://metanit.com/java/android/).
* Retrofit 2 в Android - [ссылка](https://developer.alexanderklimov.ru/android/library/retrofit.php).
* Spring | Learn - [ссылка](https://spring.io/learn).
* Learn Spring Boot - [ссылка](https://www.baeldung.com/spring-boot).
