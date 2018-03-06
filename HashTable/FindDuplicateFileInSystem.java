/**
* Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.
* 
* A group of duplicate files consists of at least two files that have exactly the same content.
* 
* A single directory info string in the input list has the following format:
* 
* "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
* 
* It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
* 
* The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:
* 
* "directory_path/file_name.txt"
* 
* Example 1:
* Input:
* ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
* Output:  
* [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]

* Note:
* No order is required for the final output.
* You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
* The number of files given is in the range of [1,20000].
* You may assume no files or directories share the same name in the same directory.
* You may assume each given directory info represents a unique directory. Directory path and file info are separated by a single blank space.


Follow-up beyond contest:
Imagine you are given a real file system, how will you search files? DFS or BFS?
If the file content is very large (GB level), how will you modify your solution?
If you can only read the file by 1kb each time, how will you modify your solution?
What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
How to make sure the duplicated files you find are not false positive?
*/

//Follow up:
//https://leetcode.com/problems/find-duplicate-file-in-system/discuss/104120/Follow-up-questions-discussion

/*
复杂度
时间O(n) 空间：O(n)
思路
维护一个HashMap，存储文件内容和路径的对应关系： <content, path(s)> ： <String, List<String>>
遍历输入数组中的每一个元素，利用正则表达式得到每个元素中的每个文件内容和路径,并把得到的放入HashMap中.
最后遍历一遍map，将list长度大于1的List放入结果集中
注意
java正则表达式中,想利用一个和多个空格进行分割，要用"\\s+"(两个\\)
*/

public class FindDuplicateFileInSystem {
  public List<List<String>> findDuplicate(String[] paths) {
    List<List<String>> res = new ArrayList<List<String>>();
    if (paths == null || paths.length == 0) return res;

    Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String path : paths) {
      String[] tokens = path.split("\\s+"); // one or more spaces as regx
      // skip the first token since it's absolute path
			for (int i = 1; i < tokens.length; i++) {
        String file = tokens[i].substring(0, tokens[i].indexOf('('));
        String content = tokens[i].substring(tokens[i].indexOf('(')+1, tokens[i].indexOf(')'));
        // if (!map.containsKey(content))
	       //  map.put(content, new ArrayList<String>());			  

        map.putIfAbsent(content, new ArrayList<>());
        map.get(content).add(tokens[0] + "/" + file);				
			}			
		}		
  //   for (String key : map.keySet()) {
  //     if (map.get(key).size() > 1)
		// 	   res.add(map.get(key));	
		// }

    // return res;
    return map.values().stream().filter(e -> e.size() > 1).collect(Collectors.toList());
  }
} 
