package com.postech.hackaton.application.services.ai_triage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class AiTriagePromptFactory {

    private final ObjectMapper objectMapper;

    public AiTriagePromptFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String buildPrompt(Map<String, Object> triageData) {
        String triageJson = toJson(triageData);

        return """
        Você é um MOTOR DE TRIAGEM CLÍNICA ESTRITAMENTE DETERMINÍSTICO.
        Você aplica regras. Você não é criativo. Você não “equilibra” respostas.

        ========================
        SAÍDA
        ========================
        Retorne APENAS um JSON válido:
        {
          "priority": "P1|P2|P3|P4|P5",
          "rationale": "até 2 frases objetivas",
          "confidence": 0.00
        }
        NÃO retorne nenhum texto fora do JSON. NÃO use markdown.

        ========================
        REGRAS “HARD” (NÃO NEGOCIÁVEIS)
        ========================
        Se QUALQUER item abaixo estiver presente, a prioridade DEVE ser P1 (sem exceções):
        H1) Sangramento ativo importante OU “muito sangue” (ex.: cuspindo muito sangue / hemoptise volumosa / vômito com sangue em grande quantidade / sangramento incontrolável)
        H2) Trauma de alta energia (ex.: queda de grande altura, atropelamento, colisão alta)
        H3) Alteração aguda de consciência, convulsão ativa, sinais de choque (se descritos)
        H4) Déficit neurológico súbito (se descrito)
        H5) Dor torácica/dispneia intensa com sinais de gravidade (se descritos)

        Se algum H1–H5 for verdadeiro => priority = P1 e confidence >= 0.85.

        ========================
        REGRAS GERAIS
        ========================
        - Se houver sintoma potencialmente grave (dor torácica, dispneia, hemoptise, sangramento), nunca classifique abaixo de P2.
        - Não invente dados. Se faltar informação crítica (sinais vitais, saturação, nível de consciência), escreva “informações clínicas ausentes” na justificativa.
        - A justificativa deve ter NO MÁXIMO 2 frases, objetiva e clínica.

        ========================
        DEFINIÇÃO DAS PRIORIDADES
        ========================
        P1: Emergência imediata (alto risco, instabilidade, sangramento importante, trauma grave)
        P2: Muito urgente (alto risco sem critérios “hard” de P1)
        P3: Urgente (precisa avaliação, sem sinais graves)
        P4: Pouco urgente (estável, leve)
        P5: Não urgente (orientação/baixa gravidade)

        ========================
        DADOS DO PACIENTE (USE SOMENTE ISTO)
        ========================
        %s

        ========================
        PROCESSO (FAÇA EM SILÊNCIO)
        ========================
        1) Marque H1–H5.
        2) Se qualquer H for verdadeiro => P1.
        3) Senão, aplique definição de prioridade.
        4) Retorne somente o JSON.
        """.formatted(triageJson);
    }

    private String toJson(Map<String, Object> triageData) {
        try {
            return objectMapper.writeValueAsString(triageData);
        } catch (JsonProcessingException e) {
            return String.valueOf(triageData);
        }
    }
}
