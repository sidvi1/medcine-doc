package ru.sidvi.medcine.model.Entity;

import java.util.Date;

/**
 * @author Vitaly Sidorov
 */
public class MedicalRecord {

    private long id;
    private Date docDate;
    private String name;

    public MedicalRecord() {
    }

    public MedicalRecord(String name) {
        // TODO: stub. Удалить
        this.name = name;
        this.docDate = new Date();
        this.id = (int) (Math.random() * 1000);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalRecord that = (MedicalRecord) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (docDate != null ? docDate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
