# Rappi

Introducción:
Rappi es una aplicación para la consulta de información referente a todo tipo de peliculas, tomando como base la API 'TMDB',
además de varias librerias para ofrecer al usuario la mejor experiencia de uso.

1. Capas:
 
	* Capa de Persistencia: En esta capa se encuentra todo lo referente a los datos de la aplicación; Las clases que hacen
			       parte de esta capa son:

				- MoviesDao
				- MoviesDatabase
				- Tables
				- TmdbData
	* Capa de Red: En esta capa se encuentra todo lo referente a las conexiones de red y comunicaciones con cualquier dispositivo
		       fuera del que tiene instalada la aplicación; las Clases que hacen parte de esta capa son:
	
				- ServiceBuider
				- TmdbEndpoints

	* Capa de Negocio: En esta capa se gestiona toda la lógica de la aplicación, además de que esta capa es la que se comunica con
			   la capa de persistencia para poder funcionar; Las Clases que hacen parte de esta capa son:
				
				- movieObject
				- Constants
				- LoadMovies
				- MoviesAdapter
				- SearchOfflineMovies
				- SearchOnlineMovies

	* Capa de Presentación: En esta capa se crea la interfaz de usuario y su única función es pasar información entre las acciones
				del usuario y la capa de Negocio; Las clases que hacen parte de esta capa son:

				- Detail
				- MainActivity
				- SearchOffline
				- SearchOnline

2. Responsabilidad de cada clase:

	La responsabilidad de cada clase es administrar la información de sus atributos y operar en conjunto con las demás clases del modelo
	para así resolver las operaciones y/o problemas.


***PREGUNTAS:

	1. Responsabilidad única:
		Dice que cada componente debe tener responsabilidad sobre una parte de la funcionalidad y la responsabilidad debe ser únicamente
		de esa clase o en otras palabras debe estar encapsulada por la clase. Y su propósito es proteger nuestro código frente a cambios.
	
	2. Código Limpio:
		Desde mi punto de vista el código limpio se refiere a tener buenas prácticas de programación pero con énfasis en dos principalmenta,
		la primera es que el código esté bien estructurado y funcione de manera correcta con respecto al modelo de la manera que este sea
		lo más óptimo y eficaz posible; Y segundo que tenga buenos comentarios(pocos, pero concisos y claros), además de que el mismo código
		sea autodescriptivo para quien lo está leyendo.

