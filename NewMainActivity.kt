package com.hammad.islamicdigitalstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hammad.islamicdigitalstudy.ui.theme.IslamicDigitalStudyPlatformTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IslamicDigitalStudyPlatformTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {

    var selectedItem by remember {
        androidx.compose.runtime.mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = "Library"
                        )
                    },
                    label = {
                        Text("Library")
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    label = {
                        Text("Search")
                    }
                )

                NavigationBarItem(
                    selected = selectedItem == 3,
                    onClick = { selectedItem = 3 },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "My Library"
                        )
                    },
                    label = {
                        Text("My Library")
                    }
                )
            }
        }
    ) { paddingValues ->

        when (selectedItem) {

            0 -> HomeScreen(paddingValues)

            1 -> LibraryScreen(paddingValues)

            2 -> SearchScreen(paddingValues)

            3 -> MyLibraryScreen(paddingValues)
        }
    }
}

/* =========================
   HOME SCREEN
   ========================= */

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {

    var searchText by remember {
        mutableStateOf("")
    }

    val categories = listOf(
        "Qur'an",
        "Hadith",
        "Fiqh",
        "Tafsir",
        "Aqeedah",
        "Seerah"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Islamic Digital Study",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Your personal Islamic study space",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(22.dp))

        SearchBox(
            value = searchText,
            onValueChange = {
                searchText = it
            }
        )

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle(
            title = "Continue Reading",
            action = "View all"
        )

        Spacer(modifier = Modifier.height(12.dp))

        ContinueReadingCard()

        Spacer(modifier = Modifier.height(28.dp))

        SectionTitle(
            title = "Explore Library",
            action = null
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(categories) { category ->

                CategoryCard(
                    title = category
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        SectionTitle(
            title = "Recent Activity",
            action = "View all"
        )

        Spacer(modifier = Modifier.height(12.dp))

        RecentActivityItem(
            title = "Riyad as-Salihin",
            subtitle = "Chapter: Sincerity"
        )

        RecentActivityItem(
            title = "Tafsir Ibn Kathir",
            subtitle = "Surah Al-Fatihah"
        )

        RecentActivityItem(
            title = "Al-Hidayah",
            subtitle = "Book of Purification"
        )

        Spacer(modifier = Modifier.height(30.dp))
    }
}

/* =========================
   LIBRARY SCREEN
   ========================= */

@Composable
fun LibraryScreen(
    paddingValues: PaddingValues
) {

    val categories = listOf(
        "Qur'an",
        "Hadith",
        "Tafsir",
        "Fiqh",
        "Aqeedah",
        "Seerah"
    )

    val books = listOf(
        Triple(
            "Riyad as-Salihin",
            "Imam an-Nawawi",
            "Hadith"
        ),
        Triple(
            "Tafsir Ibn Kathir",
            "Ibn Kathir",
            "Tafsir"
        ),
        Triple(
            "Al-Hidayah",
            "Burhan al-Din al-Marghinani",
            "Fiqh"
        ),
        Triple(
            "Ar-Raheeq Al-Makhtum",
            "Safiur Rahman Mubarakpuri",
            "Seerah"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Library",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Explore Islamic books and study material",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(20.dp))

        LibrarySearchBox()

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            items(categories) { category ->

                CategoryCard(
                    title = category
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Featured Books",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        books.forEach { book ->

            BookCard(
                title = book.first,
                author = book.second,
                category = book.third
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/* =========================
   SEARCH SCREEN
   ========================= */

@Composable
fun SearchScreen(
    paddingValues: PaddingValues
) {

    var searchText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Search",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Find books, topics and authors",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(22.dp))

        SearchBox(
            value = searchText,
            onValueChange = {
                searchText = it
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        if (searchText.isEmpty()) {

            Text(
                text = "Start searching",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Search across your Islamic study library.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        } else {

            Text(
                text = "Searching for \"$searchText\"",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

/* =========================
   MY LIBRARY SCREEN
   ========================= */

@Composable
fun MyLibraryScreen(
    paddingValues: PaddingValues
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "My Library",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Your personal study space",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        LibraryStatCard(
            title = "Continue Reading",
            subtitle = "Pick up where you left off"
        )

        LibraryStatCard(
            title = "Bookmarks",
            subtitle = "Saved pages and references"
        )

        LibraryStatCard(
            title = "Favorites",
            subtitle = "Your favorite books and content"
        )

        LibraryStatCard(
            title = "Reading History",
            subtitle = "Recently opened content"
        )

        LibraryStatCard(
            title = "Notes",
            subtitle = "Your personal study notes"
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/* =========================
   SEARCH BOX
   ========================= */

@Composable
fun SearchBox(
    value: String,
    onValueChange: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                MaterialTheme.colorScheme.surfaceVariant
            )
            .padding(
                horizontal = 16.dp,
                vertical = 14.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.width(10.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
            decorationBox = { innerTextField ->

                if (value.isEmpty()) {

                    Text(
                        text = "Search books, topics, authors...",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                innerTextField()
            }
        )
    }
}

/* =========================
   LIBRARY SEARCH BOX
   ========================= */

@Composable
fun LibrarySearchBox() {

    var searchText by remember {
        mutableStateOf("")
    }

    SearchBox(
        value = searchText,
        onValueChange = {
            searchText = it
        }
    )
}

/* =========================
   SECTION TITLE
   ========================= */

@Composable
fun SectionTitle(
    title: String,
    action: String?
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        if (action != null) {

            Text(
                text = action,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

/* =========================
   CONTINUE READING CARD
   ========================= */

@Composable
fun ContinueReadingCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(
                            MaterialTheme.colorScheme.primary
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Riyad as-Salihin",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Chapter: Sincerity",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "68% completed",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(7.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(7.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        MaterialTheme.colorScheme.surface
                    )
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.68f)
                        .height(7.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            MaterialTheme.colorScheme.primary
                        )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Continue Reading",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Continue"
                )
            }
        }
    }
}

/* =========================
   CATEGORY CARD
   ========================= */

@Composable
fun CategoryCard(
    title: String
) {

    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Text(
            text = title,
            modifier = Modifier.padding(
                horizontal = 18.dp,
                vertical = 14.dp
            ),
            fontWeight = FontWeight.Medium
        )
    }
}

/* =========================
   BOOK CARD
   ========================= */

@Composable
fun BookCard(
    title: String,
    author: String,
    category: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.MenuBook,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = author,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = category,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }

            Icon(
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = "Bookmark",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/* =========================
   RECENT ACTIVITY
   ========================= */

@Composable
fun RecentActivityItem(
    title: String,
    subtitle: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/* =========================
   MY LIBRARY CARD
   ========================= */

@Composable
fun LibraryStatCard(
    title: String,
    subtitle: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        MaterialTheme.colorScheme.primaryContainer
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {

                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
