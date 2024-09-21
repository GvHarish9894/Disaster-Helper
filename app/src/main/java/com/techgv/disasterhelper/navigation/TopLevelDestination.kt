package com.techgv.disasterhelper.navigation

/**
 * This class represents all the main screens in the app.
 */
sealed class TopLevelDestination(
    val title: String,
    val route: String
) {
    data object HomeScreen : TopLevelDestination(
        title = "Home",
        route = "home"
    )

    data object Detail : TopLevelDestination(
        title = "Detail",
        route = "detail"
    )

    data object VolunteerScreen : TopLevelDestination(
        title = "Volunteer Details",
        route = "volunteer"
    )

    data object CommunityScreen : TopLevelDestination(
        title = "Community Details",
        route = "community"
    )

    data object HelpLineScreen : TopLevelDestination(
        title = "HelpLine Details",
        route = "helpline"
    )

    data object HelpArticles : TopLevelDestination(
        title = "Help Articles",
        route = "help_articles"
    )

    /**
     * This is a helper function to pass arguments to navigation destination.
     * For example, instead of using [TopLevelDestination.Detail.route]/first_argument/second_argument
     * you can use like [TopLevelDestination.Detail.withArgs(first_argument, second_argument)]
     */
    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
