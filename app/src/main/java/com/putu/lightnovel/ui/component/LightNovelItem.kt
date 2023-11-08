package com.putu.lightnovel.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.putu.lightnovel.R
import com.putu.lightnovel.database.LightNovelEntity
import com.putu.lightnovel.ui.navigation.Screen
import com.putu.lightnovel.ui.theme.LightNovelTheme

@Composable
fun LightNovelItem (
    lightNovel: LightNovelEntity,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val (id, title, subTitle, @DrawableRes image) = lightNovel

    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.large,
        elevation = 5.dp,
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.DetailLightNovel.createRoute(id))
            }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = title,
                modifier = Modifier
                    .size(100.dp, 150.dp)
                    .padding(10.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 2.dp),
                verticalArrangement = Arrangement.Top

            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = subTitle,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun LightNovelItemPreview() {
    LightNovelTheme {
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
                storeUrl = "https://ga.sbcr.jp/sp/otonari/index.html",
            ),
            navController = rememberNavController(),
            modifier = Modifier,
        )
    }
}