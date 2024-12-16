package com.rufino.motivation.data

import com.rufino.motivation.infra.MotivationConstants
import kotlin.random.Random

class Phrase(val description: String, val categoryId: Int)

class Mock {
    private val all = MotivationConstants.FILTER.ALL
    private val smile = MotivationConstants.FILTER.SMILE
    private val sunny = MotivationConstants.FILTER.SUNNY


    private val mListPhrases = listOf<Phrase>(
        Phrase("Não sabendo que era impossível, foi lá e fez.", smile),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", smile),
        Phrase("Quando está mais escuro, vemos mais estrelas!", smile),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", smile),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", sunny),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", sunny),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sunny),
        Phrase("Você perde todas as chances que você não aproveita.", sunny),
        Phrase("Se você acredita, faz toda a diferença.", smile),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sunny),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", smile)
    )

    fun getPhrase(value: Int): String {
        val filtered =
            if (value != all) mListPhrases.filter { it.categoryId == value } else mListPhrases
        val rand = Random.nextInt(filtered.size)
        return filtered[rand].description
    }
}