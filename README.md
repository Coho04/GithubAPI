# GithubAPI

A powerful and user-friendly Java toolkit for the GitHub API, simplifying access and interaction with GitHub data for
developers.

This is the official repository for the GithubAPI project. The GithubAPI is a Java-based library designed to interact
with the GitHub API, providing a simplified interface for developers.

## Features

- **Package Structure:** Organized packages including bases, builders, entities, enums, interfaces, repositories, and
  utilities.
- **Ease of Use:** Simplifies interaction with the GitHub API.
- **Flexible:** Supports various GitHub API functionalities through a robust set of classes and methods.

## Installation

To include GithubAPI in your project, add the following dependency to your `pom.xml` (for Maven projects):

```xml
<dependency>
  <groupId>io.github.coho04</groupId>
  <artifactId>githubapi</artifactId>
  <version>${version}</version>
</dependency>
```

For Gradle projects, add the following to your build.gradle file:

```
implementation 'io.github.coho04:githubapi:${version}'
```

## Usage
Here's a basic example of how to use GithubAPI:

```java
import io.github.coho04.githubapi.Github;
import io.github.coho04.githubapi.entities.GHOrganisation;
import io.github.coho04.githubapi.entities.repositories.GHRepository;

public class Main {
    
  public static void main(String[] args) {
    Github github = new Github("your_access_token");
    GHOrganisation organisation = github.findOrganisationByName("repo_name");
    GHRepository repository = organisation.findRepositoryByName("repo_name");
    System.out.println("Repository name: " + repository.getName());
  }
}
```

## Documentation
Comprehensive documentation is available at the following links. It contains detailed information on all packages, classes and methods
provided by the library.

- [Javadoc](https://coho04.github.io/GithubAPI/)
- [Wiki](https://github.com/Coho04/GithubAPI/wiki)

## Contributing
We welcome contributions! Please read our Contributing Guide to learn about how to get involved.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgements
Special thanks to all the contributors who have helped in developing this project.

## Contact
For any queries, please contact us at cohohohn04@gmail.com.

For more detailed API information, visit the GithubAPI documentation.