package pojos;

import java.util.ArrayList;

/**
 * Created by evelyn on 18/11/2016.
 */

public class Recursos {

    //Campos para couchdb
    private String _id;


    private String _rev;
    //id
    private String nombre;
    private String descripcion;
    private String informacionGeneral;
    private String direccion;
    private ArrayList<Costo> costoRecursos = new ArrayList<Costo>();
    private ArrayList<Facilidad> facilidadRecurso = new ArrayList<Facilidad>();
    private ArrayList<Recomendacion> recomendacion = new ArrayList<Recomendacion>();
    //informacion contacto
    private ArrayList<Imagen> imagen = new ArrayList<Imagen>();
    private ArrayList<Senderos> sendero = new ArrayList<Senderos>();
    //posicion
    //estado
    private ArrayList<Idiomas> idiomasInformac = new ArrayList<Idiomas>();
    private float ranking;

    //constructor

    public Recursos(){

    }

    public Recursos(String nombre, String descripcion, String informacionGeneral, String direccion, ArrayList<Costo> costoRecursos, ArrayList<Facilidad> facilidadRecurso, ArrayList<Recomendacion> recomendacion, ArrayList<Imagen> imagen, ArrayList<Senderos> sendero, ArrayList<Idiomas> idiomasInformac, float ranking) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.informacionGeneral = informacionGeneral;
        this.direccion = direccion;
        this.costoRecursos = costoRecursos;
        this.facilidadRecurso = facilidadRecurso;
        this.recomendacion = recomendacion;
        this.imagen = imagen;
        this.sendero = sendero;
        this.idiomasInformac = idiomasInformac;
        this.ranking = ranking;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInformacionGeneral() {
        return informacionGeneral;
    }

    public void setInformacionGeneral(String informacionGeneral) {
        this.informacionGeneral = informacionGeneral;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Costo> getCostoRecursos() {
        return costoRecursos;
    }

    public void setCostoRecursos(ArrayList<Costo> costoRecursos) {
        this.costoRecursos = costoRecursos;
    }

    public ArrayList<Facilidad> getFacilidadRecurso() {
        return facilidadRecurso;
    }

    public void setFacilidadRecurso(ArrayList<Facilidad> facilidadRecurso) {
        this.facilidadRecurso = facilidadRecurso;
    }

    public ArrayList<Recomendacion> getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(ArrayList<Recomendacion> recomendacion) {
        this.recomendacion = recomendacion;
    }

    public ArrayList<Imagen> getImagen() {
        return imagen;
    }

    public void setImagen(ArrayList<Imagen> imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Senderos> getSendero() {
        return sendero;
    }

    public void setSendero(ArrayList<Senderos> sendero) {
        this.sendero = sendero;
    }

    public ArrayList<Idiomas> getIdiomasInformac() {
        return idiomasInformac;
    }

    public void setIdiomasInformac(ArrayList<Idiomas> idiomasInformac) {
        this.idiomasInformac = idiomasInformac;
    }

    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }

    //otros metodos

    public void verReviews(){
    }

    public void llamarContacto(){
    }

    public void calcularRanking(){

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    @Override
    public String toString() {
        return "Recursos{" +
                "_id='" + _id + '\'' +
                ", _rev='" + _rev + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", informacionGeneral='" + informacionGeneral + '\'' +
                ", direccion='" + direccion + '\'' +
                ", costoRecursos=" + costoRecursos +
                ", facilidadRecurso=" + facilidadRecurso +
                ", recomendacion=" + recomendacion +
                ", imagen=" + imagen +
                ", sendero=" + sendero +
                ", idiomasInformac=" + idiomasInformac +
                ", ranking=" + ranking +
                '}';
    }
}
