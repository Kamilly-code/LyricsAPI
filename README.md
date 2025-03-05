Integrantes: Kamilly Brito da Mata

Esta aplicación permite a los usuarios buscar y visualizar letras de canciones. Los usuarios pueden ingresar el nombre del artista y el título de la canción para obtener las letras correspondientes. Utiliza Jetpack Compose para la interfaz de usuario, Retrofit para las solicitudes de red y un ViewModel para gestionar los datos de la UI.

Funcionalidades:
1. **Búsqueda de Letras de Canciones**:
   - Los usuarios pueden ingresar el nombre del artista y el nombre de la canción para buscar las letras.

3. **Mostrar Letras**:
   - La aplicación obtiene y muestra las letras de la canción especificada.

4. **Indicador de Carga**:
   - Se muestra un indicador de progreso circular mientras se obtienen las letras.

5. **Manejo de Errores**:
   - Se muestran mensajes de error si hay un problema al obtener las letras.

9. **Integración con Retrofit**:
   - La aplicación utiliza Retrofit para realizar solicitudes de red y obtener las letras de las canciones.

10. **Funciones Componibles**:
    - La interfaz de usuario está construida utilizando Jetpack Compose, un kit de herramientas moderno para construir interfaces de usuario nativas en Android.



La API Lyrics.ovh es una herramienta útil para obtener letras de canciones de forma sencilla y gratuita. En la aplicación, la API se consume a través de Retrofit, y los datos se administran en ViewModel usando Flow para actualizar la interfaz reactivamente. El manejo de errores es esencial para hacer frente a canciones no encontradas o fallas de conexión, asegurando una mejor experiencia del usuario.

