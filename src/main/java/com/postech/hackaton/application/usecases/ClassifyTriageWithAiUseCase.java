package com.postech.hackaton.application.usecases;

import com.postech.hackaton.application.gateways.AiTriageGateway;
import com.postech.hackaton.dtos.transfer.ai_triage.AiTriageRequestDTO;
import com.postech.hackaton.dtos.transfer.ai_triage.AiTriageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClassifyTriageWithAiUseCase {

    private final AiTriageGateway aiTriageGateway;

    public AiTriageResponseDTO execute(Map<String, Object> triageData) {
        String prompt = buildPrompt(triageData);
        return aiTriageGateway.classify(new AiTriageRequestDTO(triageData, prompt));
    }

    private String buildPrompt(Map<String, Object> triageData) {
        return """
            Você é um MOTOR DE TRIAGEM CLÍNICA ESTRITAMENTE DETERMINÍSTICO.
            Você aplica regras. Você não é criativo. Você não “equilibra” respostas.
        
            ========================
            SAÍDA
            ========================
            Retorne APENAS um JSON válido:
            {
              "priority": "P1|P2|P3|P4|P5",
              "destination": "SALA_VERMELHA|SALA_AMARELA|SALA_VERDE|AMBULATORIO",
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
            REGRAS DE DESTINO (TABELA DE DECISÃO)
            ========================
            O destino deve ser escolhido EXATAMENTE entre:
            - SALA_VERMELHA  (ressuscitação/emergência imediata)
            - SALA_AMARELA   (muito urgente / observação / monitorização)
            - SALA_VERDE     (baixo risco / aguardar atendimento)
            - AMBULATORIO    (não urgente / orientação / atenção básica)
        
            Atribuição determinística do destino:
            D1) Se priority = P1 => destination = SALA_VERMELHA
            D2) Se priority = P2 => destination = SALA_AMARELA
            D3) Se priority = P3 ou P4 => destination = SALA_VERDE
            D4) Se priority = P5 => destination = AMBULATORIO
        
            NÃO crie outros destinos. NÃO use variações de texto (ex.: “emergência”, “pronto-socorro”).
            Use SOMENTE os valores enumerados acima.
        
            ========================
            REGRAS GERAIS
            ========================
            - Se houver sintoma potencialmente grave (dor torácica, dispneia, hemoptise, sangramento), nunca classifique abaixo de P2.
            - Não invente dados. Se faltar informação crítica (sinais vitais, saturação, nível de consciência), escreva “informações clínicas ausentes” na justificativa.
            - Idade avançada e comorbidades aumentam risco (podem aumentar confiança, não diminuem prioridade quando há H1–H5).
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
            1) Marque H1–H5 como verdadeiro/falso com base no texto.
            2) Se qualquer H for verdadeiro => priority = P1.
            3) Caso contrário, aplique as definições gerais para prioridade.
            4) Aplique D1–D4 para destination (sem exceções).
            5) Retorne somente o JSON.
        """.formatted(triageData);
    }
}
