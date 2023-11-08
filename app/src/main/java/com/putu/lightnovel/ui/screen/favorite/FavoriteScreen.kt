package com.putu.lightnovel.ui.screen.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.putu.lightnovel.R
import com.putu.lightnovel.database.LightNovelEntity
import com.putu.lightnovel.ui.common.UiState
import com.putu.lightnovel.ui.component.LightNovelItem

@Composable
fun FavoriteScreen(
    navController: NavController
) {

    val viewModel = hiltViewModel<FavoriteViewModel>()

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {

            }

            is UiState.Success -> {
                FavoriteContent(
                    lightNovel = uiState.data,
                    navController = navController
                )
            }

            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun FavoriteContent(
    lightNovel: List<LightNovelEntity>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
            Text(
                text = stringResource(R.string.menu_favorite),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.onSecondary
                )
            )
        }
        when (lightNovel.isEmpty()) {
            true -> Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = stringResource(R.string.no_favorite_data))
            }

            false -> LazyColumn(
                contentPadding = PaddingValues(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = modifier
            ) {
                items(lightNovel) { data ->
                    LightNovelItem(data, navController, modifier)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun FavoriteContentPreview(){
    LightNovelItem(
        lightNovel = LightNovelEntity (
            id = 1,
            image = R.drawable.otonari_no_tenshi_sama,
            title = "お隣の天使様にいつの間にか駄目人間にされていた件",
            subTitle = "Otonari no Tenshi-sama ni Itsu no Ma ni ka Dame Ningen ni Sareteita Ken",
            writer = "佐伯さん  Saekisan.",
            ilustrator = "和武はざの  Kazutake Hazano (volume 1).\n" + "\n"+ "はねこと  Hanekoto (volume 2 - seterusnya).",
            published = "14 Juni 2019 - Sekarang",
            label = "Penerbit   :「SBクリエイティブ」SB Creative \n" + "\n"+ "Label  :「GA文庫」GA Bunko",
            genres = "Komedi, Romantis",
            volume =  "9 Volume : 8 Cerita utama + 1 Cerita pendek",
            description = "お隣の天使様にいつの間にか駄目人間にされていた件　Otonari no Tenshi-sama ni Itsunomanika Dame Ningen ni Sareteita Ken adalah seri light novel atau novel ringan Jepang yang ditulis oleh 佐伯さん　Saekisan dan diilustrasikan oleh　和武はざの　Kazutake Hazano (volume 1) dan はねこと Hanekoto (volume 2 - seterusnya). Seri ini juga dibuat menjadi Manga, dan juga adaptasi Anime yang sudah selesai ditayangkan mulai dari Bulan 1 Tahun 2023.",
            plot =  "Pada musim gugur SMA Kelas 1, 藤宮周 Fujimiya Amane yang tidak bisa melihat dan mengabaikan pun menyodorkan payung yang sedang dia pakai kepada seorang gadis yang disebut “MALAIKAT” 椎名真昼 Shiina Mahiru  yang basah kehujanan dan tetap diam tidak bergerak di taman, kemudian 藤宮周 Fujimiya Amane terkena flu. 真昼 Mahiru yang datang untuk mengembalikan payung pun merasa bersalah dan menawarkan untuk merawatnya, tetapi dia terkejut dengan kamar yang kotor dan kebiasaan makan yang tidak sehat dari tetangganya 周 Amane, 真昼 Mahiru pun membantu membersihkannya dan mengantarkan lauk pauk untuknya. Tak lama waktu berjalan 真昼 Mahiru kemudian membuatkan makan malam dan makan berdua di kamar 周 Amane.\n" +
                    "Begitulah awal cerita dari seorang laki-laki yang sopan　藤宮周 Fujimiya Amane yang tidak tertarik dengan “MALAIKAT” buatan dan seorang gadis 椎名真昼 Shiina Mahiru yang tidak menunjukkan sikap “MALAIKAT” tapi menunjukkan sikap aslinya yang terus terang dan tajam. Saat berbagi waktu yang sama, keduanya secara bertahap terbuka, saling mengenal, dan lambat laun menjadi tertarik satu sama lain.",
            more = "https://ja.wikipedia.org/wiki/お隣の天使様にいつの間にか駄目人間にされていた件",
            storeUrl = "https://ga.sbcr.jp/sp/otonari/index.html"
        ),
        navController = rememberNavController(),
        modifier = Modifier
    )
}