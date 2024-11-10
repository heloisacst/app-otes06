package com.app.scheduling.paciente;

import com.app.scheduling.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPaciente(
        @NotBlank
        String pacte_nome,
        @NotBlank
        @Email
        String pacte_email,
        @NotBlank
        String pacte_telefone,

        @NotNull @Valid
        DadosEndereco endereco) {}