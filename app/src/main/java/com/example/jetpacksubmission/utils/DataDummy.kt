package com.example.jetpacksubmission.utils

import com.example.jetpacksubmission.data.Details
import com.example.jetpacksubmission.data.Film
import com.example.jetpacksubmission.data.source.remote.response.DetailResponse
import com.example.jetpacksubmission.data.source.remote.response.FilmResponse

object DataDummy {
    fun generateDummyMovies(): List<Film> {
        val movies = ArrayList<Film>()

        movies.add(
            Film(
                718444,
                "Rogue",
                "2020-08-20",
                "Battle-hardened O’Hara leads a lively mercenary team of soldiers on a daring mission: rescue hostages from their captors in remote Africa. But as the mission goes awry and the team is stranded, O’Hara’s squad must face a bloody, brutal encounter with a gang of rebels.",
                "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg",
                6.0,
                386,
                822187
            )
        )
        movies.add(
            Film(
                581392,
                "Peninsula",
                "2020-07-15",
                "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula",
                "/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                7.2,
                386,
                822187
            )
        )
        movies.add(
            Film(
                605116,
                "Project Power",
                "2020-08-14",
                "An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers.",
                "/TnOeov4w0sTtV2gqICqIxVi74V.jpg",
                6.7,
                1142,
                655917
            )
        )
        movies.add(
            Film(
                613504,
                "After We Collided",
                "2020-09-02",
                "Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever.",
                "/kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
                7.1,
                206,
                651426
            )
        )
        movies.add(
            Film(
                606234,
                "Archive",
                "2020-08-13",
                "2038: George Almore is working on a true human-equivalent AI, and his latest prototype is almost ready. This sensitive phase is also the riskiest as he has a goal that must be hidden at all costs—being reunited with his dead wife.",
                "/eDnHgozW8vfOaLHzfpHluf1GZCW.jpg",
                5.9,
                66,
                497771
            )
        )

        return movies
    }

    fun generateDummyShows(): List<Film> {
        val shows = ArrayList<Film>()

        shows.add(
            Film(
                76479,
                "The Boys",
                "2019-07-25",
                "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                8.4,
                1658,
                2137823
            )
        )
        shows.add(
            Film(
                85723,
                "Raised by Wolves",
                "2020-09-03",
                "After Earth is ravaged by a great religious war, an atheistic android architect sends two of his creations, Mother and Father, to start a peaceful, godless colony on the planet Kepler-22b. Their treacherous task is jeopardized by the arrival of the Mithraic, a deeply devout religious order of surviving humans.",
                "/mTvSVKMn2Npf6zvYNbGMJnYLtvp.jpg",
                7.5,
                136,
                620045
            )
        )
        shows.add(
            Film(
                48866,
                "The 100",
                "2014-03-19",
                "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
                "/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
                7.7,
                4214,
                52196
            )
        )
        shows.add(
            Film(
                82816,
                "Lovecraft Country",
                "2020-08-16",
                "The anthology horror series follows 25-year-old Atticus Freeman, who joins up with his friend Letitia and his Uncle George to embark on a road trip across 1950s Jim Crow America to find his missing father. They must survive and overcome both the racist terrors of white America and the malevolent spirits that could be ripped from a Lovecraft paperback.",
                "/fz7bdjxPColvEWCGr5Kiclzc86d.jpg",
                7.2,
                143,
                247114
            )
        )
        shows.add(
            Film(
                98986,
                "Uzaki-chan Wants to Hang Out!",
                "2020-07-10",
                "Shinichi Sakurai’s one wish is for a little peace and quiet. But Hana Uzaki — his boisterous, well-endowed underclassman — has other plans. All she wants is to hang out and poke fun at him. With the help of her chipper charm and peppy persistence, this might just be the start of a beautiful relationship!",
                "/mnQkaU3K1ITREihPOdYyTCGUNJg.jpg",
                7.3,
                7,
                171198
            )
        )

        return shows
    }

    fun generateDummyMovieDetails(): Details {
        val arrayGenres = ArrayList<String>()
        arrayGenres.add("Action")

        return Details(
            arrayGenres,
            null,
            106,
            "When the hunter becomes the prey."
        )
    }

    fun generateDummyShowDetails(): Details {
        val arrayGenres = ArrayList<String>()
        val arrayRunTimes = ArrayList<Int>()
        arrayGenres.add("Sci-Fi & Fantasy")
        arrayGenres.add("Action & Adventure")
        arrayRunTimes.add(60)

        return Details(
            arrayGenres,
            arrayRunTimes,
            null,
            null
        )
    }

    fun generateDummyMovieResponses(): List<FilmResponse> {
        val movies = ArrayList<FilmResponse>()

        movies.add(
            FilmResponse(
                718444,
                "Rogue",
                "2020-08-20",
                "Battle-hardened O’Hara leads a lively mercenary team of soldiers on a daring mission: rescue hostages from their captors in remote Africa. But as the mission goes awry and the team is stranded, O’Hara’s squad must face a bloody, brutal encounter with a gang of rebels.",
                "/uOw5JD8IlD546feZ6oxbIjvN66P.jpg",
                6.0,
                386,
                822187
            )
        )
        movies.add(
            FilmResponse(
                581392,
                "Peninsula",
                "2020-07-15",
                "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula",
                "/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                7.2,
                386,
                822187
            )
        )
        movies.add(
            FilmResponse(
                605116,
                "Project Power",
                "2020-08-14",
                "An ex-soldier, a teen and a cop collide in New Orleans as they hunt for the source behind a dangerous new pill that grants users temporary superpowers.",
                "/TnOeov4w0sTtV2gqICqIxVi74V.jpg",
                6.7,
                1142,
                655917
            )
        )
        movies.add(
            FilmResponse(
                613504,
                "After We Collided",
                "2020-09-02",
                "Tessa finds herself struggling with her complicated relationship with Hardin; she faces a dilemma that could change their lives forever.",
                "/kiX7UYfOpYrMFSAGbI6j1pFkLzQ.jpg",
                7.1,
                206,
                651426
            )
        )
        movies.add(
            FilmResponse(
                606234,
                "Archive",
                "2020-08-13",
                "2038: George Almore is working on a true human-equivalent AI, and his latest prototype is almost ready. This sensitive phase is also the riskiest as he has a goal that must be hidden at all costs—being reunited with his dead wife.",
                "/eDnHgozW8vfOaLHzfpHluf1GZCW.jpg",
                5.9,
                66,
                497771
            )
        )

        return movies
    }

    fun generateDummyShowResponses(): List<FilmResponse> {
        val shows = ArrayList<FilmResponse>()

        shows.add(
            FilmResponse(
                76479,
                "The Boys",
                "2019-07-25",
                "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                8.4,
                1658,
                2137823
            )
        )
        shows.add(
            FilmResponse(
                85723,
                "Raised by Wolves",
                "2020-09-03",
                "After Earth is ravaged by a great religious war, an atheistic android architect sends two of his creations, Mother and Father, to start a peaceful, godless colony on the planet Kepler-22b. Their treacherous task is jeopardized by the arrival of the Mithraic, a deeply devout religious order of surviving humans.",
                "/mTvSVKMn2Npf6zvYNbGMJnYLtvp.jpg",
                7.5,
                136,
                620045
            )
        )
        shows.add(
            FilmResponse(
                48866,
                "The 100",
                "2014-03-19",
                "100 years in the future, when the Earth has been abandoned due to radioactivity, the last surviving humans live on an ark orbiting the planet — but the ark won't last forever. So the repressive regime picks 100 expendable juvenile delinquents to send down to Earth to see if the planet is still habitable.",
                "/wcaDIAG1QdXQLRaj4vC1EFdBT2.jpg",
                7.7,
                4214,
                52196
            )
        )
        shows.add(
            FilmResponse(
                82816,
                "Lovecraft Country",
                "2020-08-16",
                "The anthology horror series follows 25-year-old Atticus Freeman, who joins up with his friend Letitia and his Uncle George to embark on a road trip across 1950s Jim Crow America to find his missing father. They must survive and overcome both the racist terrors of white America and the malevolent spirits that could be ripped from a Lovecraft paperback.",
                "/fz7bdjxPColvEWCGr5Kiclzc86d.jpg",
                7.2,
                143,
                247114
            )
        )
        shows.add(
            FilmResponse(
                98986,
                "Uzaki-chan Wants to Hang Out!",
                "2020-07-10",
                "Shinichi Sakurai’s one wish is for a little peace and quiet. But Hana Uzaki — his boisterous, well-endowed underclassman — has other plans. All she wants is to hang out and poke fun at him. With the help of her chipper charm and peppy persistence, this might just be the start of a beautiful relationship!",
                "/mnQkaU3K1ITREihPOdYyTCGUNJg.jpg",
                7.3,
                7,
                171198
            )
        )

        return shows
    }

    fun generateDummyMovieDetailsResponse(): DetailResponse {
        val arrayGenres = ArrayList<String>()
        arrayGenres.add("Action")

        return DetailResponse(
            arrayGenres,
            null,
            106,
            "When the hunter becomes the prey."
        )
    }

    fun generateDummyShowDetailsResponse(): DetailResponse {
        val arrayGenres = ArrayList<String>()
        val arrayRunTimes = ArrayList<Int>()
        arrayGenres.add("Sci-Fi & Fantasy")
        arrayGenres.add("Action & Adventure")
        arrayRunTimes.add(60)

        return DetailResponse(
            arrayGenres,
            arrayRunTimes,
            null,
            null
        )
    }
}