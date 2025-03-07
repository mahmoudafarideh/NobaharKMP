package m.a.nobahar.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SyncAudioDto(
    @SerialName("DesktopGanjoorPoemAudioList")
    val audioList: AudioList
) {
    @Serializable
    data class AudioList(
        @SerialName("PoemAudio")
        val poemAudio: PoemAudio
    )

    @Serializable
    data class PoemAudio(
        @SerialName("OneSecondBugFix")
        val oneSecondBugFix: Long,
        @SerialName("SyncArray")
        val syncArray: List<SyncInfo>,
    )


    @Serializable
    data class SyncInfo(
        @SerialName("VerseOrder")
        val verseOrder: Int,
        @SerialName("AudioMiliseconds")
        val audioMilliseconds: Int,
    )
}
