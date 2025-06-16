package com.example.dispensadobem.service;

import com.example.dispensadobem.dto.EstabelecimentoDTO;
import com.example.dispensadobem.model.CategoriaEstabelecimento;
import com.example.dispensadobem.model.EnderecoEstabelecimento;
import com.example.dispensadobem.model.Estabelecimento;
import com.example.dispensadobem.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private CategoriaEstabelecimentoService categoriaEstabelecimentoService;

    public List<Estabelecimento> listarTodos() {
        return estabelecimentoRepository.findAll();
    }

    public Optional<Estabelecimento> buscarPorId(Long id) {
        return estabelecimentoRepository.findById(id);
    }

    public Optional<Estabelecimento> buscarPorEmail(String email) {
        return estabelecimentoRepository.findByEmail(email);
    }

    public Estabelecimento salvar(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    public void deletar(Long id) {
        estabelecimentoRepository.deleteById(id);
    }

    public Optional<Estabelecimento> autenticar(String email, String senha) {
        return estabelecimentoRepository.findByEmail(email)
                .filter(estabelecimento -> estabelecimento.getSenha().equals(senha));
    }

    public Estabelecimento fromDTO(EstabelecimentoDTO dto) {
        Estabelecimento est = new Estabelecimento();
        est.setNomeFantasia(dto.getNomeFantasia());
        est.setCnpj(dto.getCnpj());
        est.setEmail(dto.getEmail());
        est.setSenha(dto.getSenha());
        est.setHorarioFuncionamento(dto.getHorarioFuncionamento());
        est.setOfereceEntrega(dto.getOfereceEntrega());
        est.setTaxaEntrega(dto.getTaxaEntrega());
        est.setEntregaGratisAcima(dto.getEntregaGratisAcima());
        est.setDistanciaMaxEntregaKm(dto.getDistanciaMaxEntregaKm());
        est.setTelefone(dto.getTelefone());

        if (dto.getCategoria() != null && dto.getCategoria().getId() != null) {
            Optional<CategoriaEstabelecimento> categoria = categoriaEstabelecimentoService.buscarPorId(dto.getCategoria().getId());
            categoria.ifPresent(est::setCategoria);
        }

        if (dto.getEndereco() != null) {
            EnderecoEstabelecimento endereco = new EnderecoEstabelecimento();
            endereco.setRua(dto.getEndereco().getRua());
            endereco.setNumero(dto.getEndereco().getNumero());
            endereco.setComplemento(dto.getEndereco().getComplemento());
            endereco.setBairro(dto.getEndereco().getBairro());
            endereco.setCidade(dto.getEndereco().getCidade());
            endereco.setEstado(dto.getEndereco().getEstado());
            endereco.setCep(dto.getEndereco().getCep());
            endereco.setLatitude(BigDecimal.ZERO);
            endereco.setLongitude(BigDecimal.ZERO);
            endereco.setEstabelecimento(est);

            est.setEndereco(endereco);
        }

        return est;
    }

}
