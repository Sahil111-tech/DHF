/*
 * package com.qa.listeners;
 * 
 * 
 * import com.fasterxml.jackson.databind.JsonNode; import
 * com.fasterxml.jackson.databind.ObjectMapper; import org.testng.ITestContext;
 * import org.testng.ITestListener; import org.testng.ITestResult;
 * 
 * import java.io.File; import java.util.HashMap; import java.util.Map;
 * 
 * public class CucumberCustomReportGenerator implements ITestListener {
 * 
 * private int totalPassed = 0; private int totalFailed = 0; private int
 * totalSkipped = 0; private Map<String, Map<String, Integer>> tagSummary = new
 * HashMap<>();
 * 
 * @Override public void onFinish(ITestContext context) {
 * generateCustomReport(); }
 * 
 * private void generateCustomReport() { try { ObjectMapper objectMapper = new
 * ObjectMapper(); JsonNode jsonNode = objectMapper.readTree(new
 * File("target/cucumber-report.json"));
 * 
 * // Iterate through each feature for (JsonNode featureNode :
 * jsonNode.path("features")) { // Iterate through scenarios in each feature for
 * (JsonNode scenarioNode : featureNode.path("elements")) { String scenarioName
 * = scenarioNode.path("name").asText(); String scenarioStatus =
 * scenarioNode.path("status").asText(); String[] tags =
 * scenarioNode.path("tags").toString().replaceAll("[\\[\\]\"]", "").split(",");
 * 
 * // Update counts based on scenario status if
 * ("passed".equalsIgnoreCase(scenarioStatus)) { totalPassed++; } else if
 * ("failed".equalsIgnoreCase(scenarioStatus)) { totalFailed++; } else if
 * ("skipped".equalsIgnoreCase(scenarioStatus)) { totalSkipped++; }
 * 
 * // Update tag summary for (String tag : tags) { tag = tag.trim();
 * tagSummary.putIfAbsent(tag, new HashMap<>());
 * tagSummary.get(tag).put(scenarioStatus,
 * tagSummary.get(tag).getOrDefault(scenarioStatus, 0) + 1); } } }
 * 
 * printSummary();
 * 
 * } catch (Exception e) { e.printStackTrace(); } }
 * 
 * private void printSummary() { System.out.println("\nSummary Report");
 * System.out.println("Tags\t\tPassed\tFailed\tSkipped"); for (Map.Entry<String,
 * Map<String, Integer>> entry : tagSummary.entrySet()) { String tag =
 * entry.getKey(); Map<String, Integer> results = entry.getValue();
 * System.out.printf("%s\t%d\t%d\t%d%n", tag, results.getOrDefault("passed", 0),
 * results.getOrDefault("failed", 0), results.getOrDefault("skipped", 0)); }
 * System.out.printf("Total Passed: %d, Total Failed: %d, Total Skipped: %d%n",
 * totalPassed, totalFailed, totalSkipped); } }
 */