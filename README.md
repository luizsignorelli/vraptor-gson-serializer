## ABOUT

VRaptor uses Xstream to serialize JSON from controllers, we don't like Xstream.

## INSTALATION

    git clone git://github.com/luizsignorelli/vraptor-gson-serializer.git
    cd vraptor-gson-serializer
    mvn install

## CONFIGURATION

1. Add the dependency to your project
2. Add this to your web.xml:

        <context-param>
            <param-name>br.com.caelum.vraptor.packages</param-name>
            <param-value> br.com.caelum.vraptor.serialization.gson</param-value>
        </context-param>

## CHANGES

### 0.5.0

We've added a JsonInterceptor and a @Json annotation, so now you can do this:

    @Resource
    public class CustomerController {

        private final Customers customers;

        public CustomerController(Customers customers) {
            this.customers = customers;
        }

        *@Json* @Get("/customer/byName")
        public List<Customer> findByName(String name){
            return Arrays.asList(customers.findByName(name));
        }
    }

The annotation marks the method to be intercepted, and it will just use the result.use(json()) to serialize the return of the method.

You can exclude som fields too:

    @Resource
    public class CustomerController {

        private final Customers customers;

        public CustomerController(Customers customers) {
            this.customers = customers;
        }

        *@Json(exclude = {"address, age"})* @Get("/customer/byName")
        public List<Customer> findByName(String name){
            return Arrays.asList(customers.findByName(name));
        }
    }

### 0.0.2

We've implemented the exclude funcionality. It works just like the XstreamSerializer.