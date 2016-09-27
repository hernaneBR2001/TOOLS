package br.com.dbatools.domain;

public class DBAToolsCommand {
    private String executable;
    private String params;
    
    public DBAToolsCommand(String executable, String params){
        this.executable = executable;
        this.params = params;
    }

    public void setExecutable(String executable){
        this.executable = executable;
    }

    public String getExecutable(){
        return executable;
    }

    public void setParams(String params){
        this.params = params;
    }

    public String getParams(){
        return params;
    }
}