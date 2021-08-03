package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {
	
	   public static void main(String[] args) {
	
	        Locale.setDefault(Locale.US);
	        Scanner sc = new Scanner(System.in);
	          
	        System.out.print("Insira o nome do arquivo: ");
	  		String path = sc.nextLine();
	  		  
	  		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	  			
	  			List<Employee> list = new ArrayList<>();
	  			
	  			String line = br.readLine();
				while (line != null) {
					String[] fields = line.split(",");
					list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
					line = br.readLine();
	  		    }
				
				System.out.print("Insira o salário: ");
				double salary = sc.nextDouble();
				
				List<String> emails = list.stream()
						.filter(x -> x.getSalary() > salary)
						.map(x -> x.getEmail())
						.sorted()
						.collect(Collectors.toList());
				
				System.out.println("Email de pessoas cujo salário é superior a " + String.format("%.2f", salary) + ":");
				emails.forEach(System.out::println);
				
				double sum = list.stream()
						.filter(x -> x.getName().charAt(0) == 'M')
						.map(x -> x.getSalary())
						.reduce(0.0, (x, y) -> x + y);
				
				System.out.println("Soma do salário de pessoas cujo nome começa com 'M': " + String.format("%.2f", sum));
				
	  		} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());
				
	  		}
			sc.close();
	   }
}
