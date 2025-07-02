## Example project of Micronaut Annotation Mapper not working
### Description
This minimal example project follows [the documentation about mapping custom annotations](https://docs.micronaut.io/4.8.18/guide/#_aliasing_mapping_annotations) to make sure an external annotation is discovered and processed on startup according to the [Application Context specification](https://docs.micronaut.io/4.8.18/guide/#containerArch) by means of an [`ExecutableMethodProcessor`](https://docs.micronaut.io/4.8.18/api/io/micronaut/context/processor/ExecutableMethodProcessor.html). 
This however is not working.

We tried following the documentation to the letter:
- Implement the [AnnotationMapper interface](./micronaut-module/micronaut-annotations/src/main/java/com/example/micronaut/annotations/MyExternalAnnotationMicronautMapper.java)
- Define a [META-INF/services/io.micronaut.inject.annotation.AnnotationMapper](./micronaut-module/micronaut-annotations/src/main/resources/META-INF/services/io.micronaut.inject.annotation.AnnotationMapper) file referencing the implementation class
- Add the JAR file containing the implementation to the [annotationProcessor](./micronaut-application/build.gradle)

I also looked through each and every example of and searched GitHub completely for the use of [`AnnotationValue.builder(Executable.class)` on GitHub](https://github.com/search?q=AnnotationValue.builder%28Executable.class%29&type=code) 

### Project structure
- `core`: project containing the external annotation
- `micronaut-module`:
  - `micronaut-annotations`: module containing only the [MyExternalAnnotationMicronautMapper, an implementation of `TypedAnnotationMapper`](./micronaut-module/micronaut-annotations/src/main/java/com/example/micronaut/annotations/MyExternalAnnotationMicronautMapper.java) to map the [`MyExternalAnnotation`](./core/src/main/java/com/example/annotations/MyExternalAnnotation.java) to add the `Executable` annotation with `processOnStartup=true`.  
  - `micronaut-feature`: the micronaut library that adds extra functionality that can be consumed by other projects. It also contains the [`MyInternalAnnotation`](./micronaut-module/micronaut-feature/src/main/java/com/example/annotations/MyInternalAnnotation.java) which inherits the [`Executable` annotation](https://micronaut-projects.github.io/micronaut-core/latest/api/io/micronaut/context/annotation/Executable.html)
- `micronaut-application`: the micronaut application that uses the `micronaut-annotations` project for annotation processing and uses the `micronaut-feature` for handling the annotations.

### Observed behaviour
- Only the [`MyInternalAnnotation`](./micronaut-module/micronaut-feature/src/main/java/com/example/annotations/MyInternalAnnotation.java) is discovered and processed on startup.

### Expected behaviour
- Both the [`MyInternalAnnotation`](./micronaut-module/micronaut-feature/src/main/java/com/example/annotations/MyInternalAnnotation.java) and the [`MyExternalAnnotation`](./core/src/main/java/com/example/annotations/MyExternalAnnotation.java) are discovered and processed on startup.
