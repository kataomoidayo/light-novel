package com.putu.lightnovel.ui.screen.detail

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.putu.lightnovel.R
import com.putu.lightnovel.database.LightNovelEntity
import com.putu.lightnovel.ui.common.UiState
import com.putu.lightnovel.ui.theme.LightNovelTheme
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    lightNovelId: Int,
    navController: NavController,
    scaffoldState: ScaffoldState,
) {

    val viewModel = hiltViewModel<DetailLightNovelViewModel>()

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getLightNovelById(lightNovelId)
            }

            is UiState.Success -> {
                DetailContent(
                    uiState.data,
                    navController,
                    scaffoldState,
                    viewModel::updateFavoriteLightNovel
                )
            }

            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun DetailContent(
    lightNovel: LightNovelEntity,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteLightNovel: (id: Int, isFavorite: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val coroutineScope = rememberCoroutineScope()

    val (id, title, subTitle, @DrawableRes image, writer, ilustrator, published, label, genres, volume, description, plot, more, storeUrl, isFavorite) = lightNovel

    val context = LocalContext.current

    Column(modifier = modifier) {
        TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
            IconButton(
                onClick = {
                    navController.navigateUp()
                },
                modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary
                )
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = stringResource(R.string.menu_detail),
                    modifier = Modifier.padding(horizontal = 10.dp),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6.copy(
                        color = MaterialTheme.colors.onSecondary
                    )
                )
                Icon(
                    imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                    tint = if (isFavorite) Color.Red else MaterialTheme.colors.onSecondary,
                    contentDescription = title,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(10.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onUpdateFavoriteLightNovel(id, !isFavorite)
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "$title ${if (isFavorite) "dihapus dari" else "ditambahkan ke"} favorite",
                                    actionLabel = "Ok",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(5.dp)
                        .clip(RectangleShape)
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Justify
                )
                Text(
                    text = subTitle,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )
                Text(
                    text = stringResource(R.string.penulis),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = writer,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.ilustrator),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = ilustrator,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.terbit),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = published,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.penerbit),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.genre),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = genres,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.volume),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = volume,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.toko_resmi),
                    style = MaterialTheme.typography.h6
                )
                ClickableText(
                    text = AnnotatedString(storeUrl),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    ),
                    onClick = {
                        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(storeUrl))
                        context.startActivity(urlIntent)
                    }
                )
                Text(
                    text = stringResource(R.string.deskripsi),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )
                Text(
                    text = stringResource(R.string.plot),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = plot,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )
                Text(
                    text = stringResource(R.string.selengkapnya),
                    style = MaterialTheme.typography.h6
                )
                ClickableText(
                    text = AnnotatedString(more),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    ),
                    onClick = {
                        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(more))
                        context.startActivity(urlIntent)
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun DetailContentPreview() {
    LightNovelTheme {
        DetailContent(
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
            scaffoldState = rememberScaffoldState(),
            onUpdateFavoriteLightNovel = { _, _ -> },
            modifier = Modifier
        )
    }
}