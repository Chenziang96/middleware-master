package com.seu.sigar.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.hyperic.sigar.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SigarRes {
    public JSONObject jsonContainer = new JSONObject();

    public String cpuIn[] = new String[5];

    public Long ramIn[] = new Long[2];

    public void run(){
        try {
            // System信息，从jvm获取
            property();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public void runCpu(){
        try {
            Sigar sigar = new Sigar();
            CpuPerc cpu=sigar.getCpuPerc();
            printCpuPerc(cpu);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void property() throws UnknownHostException {
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");// 获取用户名
        String computerName = map.get("COMPUTERNAME");// 获取计算机名
        String userDomain = map.get("USERDOMAIN");// 获取计算机域名
        jsonContainer.put("computerName",computerName);//计算机名:
        jsonContainer.put("ip",ip);//本地ip地址
        jsonContainer.put("javaVersion",props.getProperty("java.version"));//Java的运行环境版本
        jsonContainer.put("sup",props.getProperty("java.vendor"));//Java的运行环境供应商
        jsonContainer.put("cpuAvailable",r.availableProcessors());//JVM可以使用的处理器个数
        jsonContainer.put("system",props.getProperty("os.arch"));//操作系统的构架


        System.out.println(jsonContainer);

    }

    private void printCpuPerc(CpuPerc cpu) {
        System.out.println("CPU用户使用率:    " + CpuPerc.format(cpu.getUser()));// 用户使用率
        System.out.println("CPU系统使用率:    " + CpuPerc.format(cpu.getSys()));// 系统使用率
        System.out.println("CPU当前等待率:    " + CpuPerc.format(cpu.getWait()));// 当前等待率
        System.out.println("CPU当前错误率:    " + CpuPerc.format(cpu.getNice()));//
        System.out.println("CPU当前空闲率:    " + CpuPerc.format(cpu.getIdle()));// 当前空闲率
        cpuIn[0] = CpuPerc.format(cpu.getUser());
        cpuIn[1] = CpuPerc.format(cpu.getSys());
        cpuIn[2] = CpuPerc.format(cpu.getWait());
        cpuIn[3] = CpuPerc.format(cpu.getNice());
        cpuIn[4] = CpuPerc.format(cpu.getIdle());
        for(int i=0;i<5;i++){
            int length = cpuIn[i].length();
            cpuIn[i]= cpuIn[i].substring(0,length-1);
        }
    }
    public void runRam(){
        try {
            memory();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void memory() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        // 内存总量
        System.out.println("内存总量:    " + mem.getTotal() / 1024L + "K av");
        // 当前内存使用量
        System.out.println("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
        Long total = mem.getTotal() / 1024L / 1024L;

        Long used = mem.getUsed() / 1024L /1024L;
        ramIn[0] = total;
        ramIn[1] = used;
    }

    private void file() throws Exception {
        Sigar sigar = new Sigar();
        FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
            System.out.println("分区的盘符名称" + i);
            FileSystem fs = fslist[i];
            // 分区的盘符名称
            System.out.println("盘符名称:    " + fs.getDevName());
            FileSystemUsage usage = null;
            usage = sigar.getFileSystemUsage(fs.getDirName());
            switch (fs.getType()) {
                case 0: // TYPE_UNKNOWN ：未知
                    break;
                case 1: // TYPE_NONE
                    break;
                case 2: // TYPE_LOCAL_DISK : 本地硬盘
                    // 文件系统总大小
                    System.out.println(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
                    // 文件系统剩余大小
                    System.out.println(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
                    // 文件系统可用大小
                    System.out.println(fs.getDevName() + "可用大小:    " + usage.getAvail() + "KB");
                    // 文件系统已经使用量
                    System.out.println(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
                    double usePercent = usage.getUsePercent() * 100D;
                    // 文件系统资源的利用率
                    System.out.println(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
                    break;
                case 3:// TYPE_NETWORK ：网络
                    break;
                case 4:// TYPE_RAM_DISK ：闪存
                    break;
                case 5:// TYPE_CDROM ：光驱
                    break;
                case 6:// TYPE_SWAP ：页面交换
                    break;
            }
            System.out.println(fs.getDevName() + "读出：    " + usage.getDiskReads());
            System.out.println(fs.getDevName() + "写入：    " + usage.getDiskWrites());
        }
        return;
    }

}
