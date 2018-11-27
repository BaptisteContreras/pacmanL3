package model;

import java.io.Serializable;
import java.util.Date;

public class MetaData implements Serializable {

    private String name;
    private double version;
    private TypeMap type;
    private Date created_at;
    private Date updated_at;
    private int nbLoad;
    private int nbPlayed;
    private int nbEdit;
    private boolean canBeEdit;

    public MetaData(String name, double version, TypeMap type, boolean canBeEdit) {
        this.name = name;
        this.version = version;
        this.type = type;
        this.canBeEdit = canBeEdit;
    }

    public double getVersion() {
        return version;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public boolean isCanBeEdit() {
        return canBeEdit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeMap getType() {
        return type;
    }

    public void setType(TypeMap type) {
        this.type = type;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getNbLoad() {
        return nbLoad;
    }

    public void setNbLoad(int nbLoad) {
        this.nbLoad = nbLoad;
    }

    public int getNbPlayed() {
        return nbPlayed;
    }

    public void setNbPlayed(int nbPlayed) {
        this.nbPlayed = nbPlayed;
    }

    public int getNbEdit() {
        return nbEdit;
    }

    public void setNbEdit(int nbEdit) {
        this.nbEdit = nbEdit;
    }
}
