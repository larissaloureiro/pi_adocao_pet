package br.com.pi_adocao_pet.domain.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacinaVO extends RepresentationModel<VacinaVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	private String tipo;
	private String fabricante;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fabricante, key, tipo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		VacinaVO other = (VacinaVO) obj;
		return Objects.equals(fabricante, other.fabricante) && Objects.equals(key, other.key)
				&& Objects.equals(tipo, other.tipo);
	}

}
