package m.a.nobahar.api.helper

import m.a.nobahar.api.contract.PoemApi
import m.a.nobahar.api.model.SyncAudioDto


class AudioSyncHelper(private val poemApi: PoemApi) {

    suspend fun getAudioSync(syncUrl: String): List<Pair<Int, Int>> {
        val response = poemApi.loadRecitationAudioSync(syncUrl)
        val text = response
            .removeSurrounding("\"", "\"")
            .replace("\\n", "")
            .replace("\\r", "")
        return parseVerseOrderToStartTime(text)
    }

    private fun parseVerseOrderToStartTime(xmlString: String): List<Pair<Int, Int>> {
        val verseList = mutableListOf<Pair<Int, Int>>()
        val audioSync = parseXML(SyncAudioDto.AudioList.serializer(), xmlString)
        audioSync.poemAudio.syncArray.syncInfo.forEach {
            verseList.add(it.verseOrder to it.audioMilliseconds)
        }
        return verseList.filter { it.first >= 0 }.sortedBy { it.first }
    }
}