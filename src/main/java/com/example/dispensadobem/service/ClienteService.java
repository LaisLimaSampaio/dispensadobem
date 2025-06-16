package com.example.dispensadobem.service;

import com.example.dispensadobem.dto.ClienteDTO;
import com.example.dispensadobem.dto.EnderecoClienteDTO;
import com.example.dispensadobem.model.Cliente;
import com.example.dispensadobem.model.EnderecoCliente;
import com.example.dispensadobem.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Cliente salvar(Cliente cliente) {
        if (cliente.getEndereco() != null) {

            cliente.getEndereco().setCliente(cliente);
        }
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }


    public Optional<Cliente> autenticar(String email, String senha) {
        return clienteRepository.findByEmail(email)
                .filter(cliente -> cliente.getSenha().equals(senha));
    }

    // Exemplo no ClienteService

    public Cliente fromDTO(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());
        cliente.setTelefone(dto.getTelefone());

        if (dto.getEndereco() != null) {
            EnderecoCliente endereco = new EnderecoCliente();
            EnderecoClienteDTO eDto = dto.getEndereco();

            endereco.setRua(eDto.getRua());
            endereco.setNumero(eDto.getNumero());
            endereco.setComplemento(eDto.getComplemento());
            endereco.setBairro(eDto.getBairro());
            endereco.setCidade(eDto.getCidade());
            endereco.setEstado(eDto.getEstado());
            endereco.setCep(eDto.getCep());

            endereco.setCliente(cliente);
            cliente.setEndereco(endereco);
        }

        return cliente;
    }



}
