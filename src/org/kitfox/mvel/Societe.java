package org.kitfox.mvel;

public class Societe {
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Societe [id=" + id + ", type=" + type + ", socemettrice=" + socemettrice + "]";
    }

    String id;
    String type;
    Client socemettrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Client getSocemettrice() {
        return socemettrice;
    }

    public void setSocemettrice(Client socemettrice) {
        this.socemettrice = socemettrice;
    }
}
