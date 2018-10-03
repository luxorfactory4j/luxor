# Luxor

##### Description
Luxor helps and facilitates the creation of factory, leaving the work focused only on *business rule*.


## Simple example:

##### Interface

```
public interface Equipament {

    void process(BigDecimal valor);
}

```

##### Impl example:

```
@FactoryImplRegister(key = "Boot")
public class BootEquipament extends AbstractEquipament {

    @Override
    public void process(BigDecimal value) {
        log.info("Boot impl value: " + value);
    }
}
```
```
@FactoryImplRegister(key = "Fone")
public class FoneEquipament implements Equipament {

    public void process(BigDecimal valor) {
        log.info("Process with value: " + valor);
    }
}
```

* The annotation @FactoryImplRegister maps the implementation, *key* will be used to discover the implementation

##### Discovery example :

```
Optional<Equipament> foneEquipament = FactoryImplDiscovery.discovery("Fone", Equipament.class);
  
Equipament equipament = foneEquipament.get();

equipament.process(new BigDecimal("100"));
```

## Spring integration

##### Configure the bean

```
@Autowired
private ApplicationContext applicationContext;

@Bean
public SpringFactoryImplDiscovery factoryImplDiscovery() {
    return new SpringFactoryImplDiscovery(applicationContext);
}
```

#### Spring example

```
@RestController
@RequestMapping("/calculator")
public class LuxorApi {

    @Autowired
    private SpringFactoryImplDiscovery springFactoryImplDiscovery;

    // Operation (+,-,*)
    @GetMapping("/execute/{operation}")
    public ResponseEntity teste(@PathVariable("operation") String operation) {

        Calculator calculator = springFactoryImplDiscovery.discoveryBeanOrThrow(operation, Calculator.class);

        return ResponseEntity.ok(calculator.execute(100D, 20D));
    }

}

```

##### Interface or abstract class

```
public interface Calculator {
    Double execute(@NonNull Double value1, @NonNull Double value2);
}
```

##### Impls example

```
@FactoryImplRegister(key = "+")
@Service
public class Addition implements Calculator {

    @Override
    public Double execute(Double value1, Double value2) {
        return value1 + value2;
    }
}
```
```
@FactoryImplRegister(key = "*")
@Service
public class Multiplication implements Calculator {

    @Override
    public Double execute(Double value1, Double value2) {
        return value1 * value2;
    }
}
```
```
@FactoryImplRegister(key = "-")
@Service
public class Subtraction implements Calculator {

    @Override
    public Double execute(Double value1, Double value2) {
        return value1 - value2;
    }
}

```


##### Install:
```
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
  
<dependency>
    <groupId>com.github.luxorfremaework</groupId>
    <artifactId>luxor</artifactId>
    <version>0.2-a868f60c4a-1</version>
</dependency>
 ```
