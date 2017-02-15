package admsistemas.AdmFormularios;

import java.io.Serializable;
import java.util.Comparator;

public class OrdermComparator implements Comparator, Serializable {

	private static final long serialVersionUID = 3129059394138224212L;

	@Override
	public int compare(Object o1, Object o2) {
		Integer intOrdem1 = ((Campo) o1).getOrderm();
		Integer intOrdem2 = ((Campo) o2).getOrderm();

		return intOrdem1.compareTo(intOrdem2);
	}
}