# Overview

I've taken the following challenge to create a project as a study for different mobile development concepts. The app is a movies app that consumes [The Movie Db API](https://www.themoviedb.org/) and displays:

- Now playing movies
- Popular movies
- Upcoming movies
- Detailed information about these movies, including a list of recommendations based on the movie.

## Stack

- Kotlin
- Jetpack Compose
- Clean Architecture (separate modules for app, domain, data layers, including a separate module for usecases)
- MVVM
- Kotlin Flow
- Hilt for dependency injection
- Retrofit for consuming the API data (including logging interceptor for debugging)
- Compose NavController
- BuildConfig for storing API key on local properties
- legacyBackgroundBlur for glassmorphic design on some components (Ex.: Movie genre list).

## Future improvement

- Some UI optimizations needed to ensure better user experience.
- Improve color contrast for better accessibility.

## Demo video



----------------------------------------------------------------------------

# Desafio Mobile - 2019

Bem Vindo ao desafio mobile da CI&T, por favor siga as instruções a baixo para realizar o desafio 😀.

## Instruções

- Faça um fork desse repositório e crie sua solução para iOS ou Android;
- Ao terminar a solução realize um Pull Request;
- Comente no readme do repositório os métodos utilizados, tais como arquitetura, linguagem, soluções técnicas e etc.;

## O App

<img src="screenshots/ss01.png?raw=true" width="250"> <img src="screenshots/ss02.png?raw=true" width="250"> <img src="screenshots/ss03.png?raw=true" width="250">

#### Filmes Populares

Como usuário, gostaria de ver a lista de de filmes mais populares em cartaz nos cinemas. Os itens dessa lista deverão conter:
 - O banner do filme;
 - O nome do filme;
 - A data de estreia;

#### Em Breve

Como usuário, gostária de conseguir filtrar a lista de filmes populares para que agora mostre os filmes que entrarão em cartaz em breve:
 - O Filtro deverá ser aplicado na lista já existente, implementada na task anterior

#### Detalhes do filme

Como usuário, ao selecionar um item da lista, gostaria de ver os detalhes do filme:
 - Uma imagem em alta resolução;
 - Nome do filme
 - Generos do filme
 - Ano de estreia
 - Duração
 - Elenco principal 
 - Sinópse
 
## Requisitos
 - O app deve ser desenvolvido para suportar as orientação Portrait e Landscape
 - Seja criativo, as imagens de referência são apenas exemplos, você pode criar seu próprio layout
 - Use libs e frameworks que você estiver mais acostumado
 - Teste o seu código ;D
 - Utilize a The Movie Database para realizar as consultas 
 -- TMDB (https://www.themoviedb.org)
 -- API (https://www.themoviedb.org/documentation/api)
 
# BOA SORTE!
 
 
