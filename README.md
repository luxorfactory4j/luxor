# luxor

Example:

Interface

```
public interface Equipament {

    void process(BigDecimal valor);
}

```

Impl example:

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

Discovery example :

```
Optional<Equipament> foneEquipament = factoryEngine.discovery("Fone", Equipament.class);
  
Equipament equipament = foneEquipament.get();

equipament.process(new BigDecimal("100"));
```

Install:
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
    <version>Tag</version>
</dependency>
 ```
