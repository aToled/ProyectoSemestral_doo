package Logica.Identificables;

/**
 * Clase base abstracta para entidades identificables mediante un ID único, además,
 * proporciona implementaciones estándar de equals() y hashCode() basadas en el ID.
 * @see Identificable
 */
public abstract class IdentificableAbstracta implements Identificable{
    private final String id;

    protected IdentificableAbstracta(String id){
        this.id=id;
    }

    @Override
    public String getId(){
        return id;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Identificable otro)) return false;
        return getId().equals(otro.getId());
    }

    @Override
    public final int hashCode() {
        if(id != null){
            return id.hashCode();
        } else {
            return 0;
        }
    }
}