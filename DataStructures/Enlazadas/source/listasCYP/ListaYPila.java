package listasCYP;

import java.util.Arrays;
import java.util.Iterator;

import exceptions.ElementoRepetidoException;

public class ListaYPila<T extends Comparable<T>> implements ILista<T>, IPilaEncadenada<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Nodo<T> primero;
	/**
	 * 
	 */
	private Nodo<T> ultimo;
	/**
	 * 
	 */
	private int cantidadDeElementos;
	/**
	 * constructor de la Lista; esta lista tambien puede ser llamada Pila; pues se comporta como esa estructura de Datos
	 */
	public ListaYPila(){
		cantidadDeElementos=0;
		primero=null;
		ultimo=null;
	}
	/**
	 * 
	 */
	@Override
	public void agregar(T elemento) throws ElementoRepetidoException {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		if(primero==null)
		{
			primero = nuevo;
			ultimo=primero;
			cantidadDeElementos++;
		}
		else
		{
			Nodo<T> actual= primero;
			boolean acabo=false;
			while (!acabo) {
				if(actual.darElemento().compareTo(elemento)==0)
				{
					//solo por precaucion
					acabo=true;
					throw new ElementoRepetidoException(elemento);
				}
				else
				{
					if(actual.darSiguiente()==null)
					{
						acabo=true;
					}
					else
					{
						actual=actual.darSiguiente();
					}
				}
			}
			ultimo.cambiarSiguiente(nuevo);
			ultimo=nuevo;
			cantidadDeElementos++;
		}
	}
	/**
	 * 
	 */
	public T buscar(T elemento)
	{
		Nodo<T> actual= primero;
		while (actual!=null) {
			T elObjeto=actual.darElemento();
			if(elObjeto.compareTo(elemento)==0)
			{
				return elObjeto;
			}
			actual= actual.darSiguiente();
		}
		return null;
	}
	/**
	 * 
	 */
	@Override
	public T eliminar(T t) {
		if(primero!=null)
		{
			Nodo<T> actual= primero;
			Nodo<T> siguiente= actual.darSiguiente();
			if(primero.darSiguiente()==null)
			{
				primero=null;
				if(actual.darElemento().compareTo(t)==0)
				{
					cantidadDeElementos--;
					return actual.darElemento();
				}
				return null;
			}
			if(primero.darElemento().compareTo(t)==0)
			{
				primero = primero.darSiguiente();
				cantidadDeElementos--;
				return actual.darElemento();
			}
			while (siguiente!=null) {
				if(siguiente.darElemento().compareTo(t)==0)
				{
					actual.cambiarSiguiente(siguiente.darSiguiente());
					cantidadDeElementos--;
					return siguiente.darElemento();
				}
				actual= actual.darSiguiente();
				siguiente= actual.darSiguiente();
			}
			return null;
		}
		return null;
	}
	/**
	 * 
	 */
	@Override
	public boolean vaciar() {
		if(primero==null||ultimo==null)
		{
			return false;
		}	
		else{
			primero=null;
			ultimo=null;
			cantidadDeElementos = 0;
			return true;
		}
	}
	/**
	 * 
	 */
	@Override
	public boolean esVacia() {
		return (primero==null||ultimo==null);
	}
	/**
	 * 
	 */
	@Override
	public Object[] sort() {
		Object[] aux=darEnArreglo();
		Arrays.sort(aux);
		return aux;
	}
	// -----------------------------------------------------------------
	// Metodos Para Ambos (Pila y lista )
	// -----------------------------------------------------------------
	/**
	 * 
	 */
	@Override
	public int darCantidadDeElementos() {
		return cantidadDeElementos;
	}
	/**
	 * 
	 */
	@Override
	public Object[] darEnArreglo() {
		Nodo<T> actual= primero;
		Object[] aRetornar= new Object[cantidadDeElementos];
		int contador=0;
		while (actual!=null) {
			aRetornar[contador]= actual.darElemento();
			actual= actual.darSiguiente();
			contador++;
		}
		return aRetornar;
	}
	/**
	 * 
	 */
	@Override
	public Iterator<T> iterator() {
		return new IteradorLista<T>(primero, this);
	}
	// -----------------------------------------------------------------
	// Metodos para usar como Pila
	// -----------------------------------------------------------------
	/**
	 * 
	 */
	@Override
	public void agregarEnPila(T elemento) {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		if(primero!=null)
		{
			ultimo.cambiarSiguiente(nuevo);
			ultimo=nuevo;
			cantidadDeElementos++;
		}
		else
		{
			primero=nuevo;
			ultimo=nuevo;
			cantidadDeElementos++;
		}
	}
	/**
	 * 
	 */
	@Override
	public T tomarElemento() {
		if(primero!=null)
		{
			T elem= primero.darElemento();
			Nodo<T> nuevoPrimero=primero.darSiguiente();
			primero=nuevoPrimero;
			cantidadDeElementos--;
			return elem;
		}
		return null;
	}
}